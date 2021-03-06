package fr.epf.plantes_connectees

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import fr.epf.plantes_connectees.api.InfosIrrigationsService
import fr.epf.plantes_connectees.api.InfosMesuresService
import fr.epf.plantes_connectees.api.InfosPlantesService
import fr.epf.plantes_connectees.data.AppDatabasePlantes
import fr.epf.plantes_connectees.data.ListPlantObject
import fr.epf.plantes_connectees.model.Irrigation
import fr.epf.plantes_connectees.model.Mesure
import fr.epf.plantes_connectees.model.Plante
import fr.epf.plantes_connectees.mqtt.Mqtt
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    val planteslist: MutableList<Plante> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listPlantesRecyclerview = findViewById<RecyclerView>(R.id.list_plantes_recyclerview)

        listPlantesRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)


        synchroApi()
        //if (checkForInternet(this)) {
        //}

        //DAO
        val dbPlante = Room.databaseBuilder(
            applicationContext,
            AppDatabasePlantes::class.java, "database-name"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        val plantesDao = dbPlante.planteDao()

        ListPlantObject.initializeDAO(plantesDao)

        ListPlantObject.updateDao(planteslist)
        val plants = ListPlantObject.getListPlant()
        if (plants != null) {
            listPlantesRecyclerview.adapter = PlanteAdapter(plants)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.reload -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun synchroApi() {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val station = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://azammouri.com/pc/uploads/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(station)
            .build()

        val serviceplantes = retrofit.create(InfosPlantesService::class.java)
        val servicemesures = retrofit.create(InfosMesuresService::class.java)
        val serviceirrigations = retrofit.create(InfosIrrigationsService::class.java)

        runBlocking {

            val result2 = serviceplantes.getInfosPlantes()
            val infosplantes = result2.data.plantes

            infosplantes.map {
                val(Adresse_Mac_plante, Libelle_plante, Date_plantation_plante, Description_plante,Niveau_irrigation_plante,Seuil_humidite_plante) = it

                val result1 = servicemesures.getInfosMesures(Adresse_Mac_plante,20)
                val infosmesures = result1.data.mesures
                val mesuresplantelist: MutableList<Mesure> = mutableListOf()

                val result3 = serviceirrigations.getInfosIrrigations(Adresse_Mac_plante,20)
                val infosirrigations = result3.data.irrigations
                val irrigationsplantelist: MutableList<Irrigation> = mutableListOf()

                if(infosmesures == null && infosirrigations == null){
                    Plante(
                        Adresse_Mac_plante, Libelle_plante, Date_plantation_plante, Description_plante, Niveau_irrigation_plante, Seuil_humidite_plante,
                        mesuresplantelist, irrigationsplantelist
                    )
                } else if(infosmesures != null && infosirrigations == null){
                    Plante(
                        Adresse_Mac_plante, Libelle_plante, Date_plantation_plante, Description_plante, Niveau_irrigation_plante, Seuil_humidite_plante,
                        infosmesures as MutableList<Mesure>, irrigationsplantelist
                    )
                }else if(infosmesures == null && infosirrigations != null) {
                    Plante(
                        Adresse_Mac_plante, Libelle_plante, Date_plantation_plante, Description_plante, Niveau_irrigation_plante, Seuil_humidite_plante,
                        mesuresplantelist, infosirrigations as MutableList<Irrigation>
                    )
                }else{
                    Plante(
                        Adresse_Mac_plante, Libelle_plante, Date_plantation_plante, Description_plante, Niveau_irrigation_plante, Seuil_humidite_plante,
                        infosmesures as MutableList<Mesure>, infosirrigations as MutableList<Irrigation>
                    )
                }

            }.map {
                planteslist.add(it)
            }

        }
    }


    @SuppressLint("MissingPermission")
    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}
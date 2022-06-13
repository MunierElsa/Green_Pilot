package fr.epf.plantes_connectees

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import fr.epf.plantes_connectees.api.InfosPlantesService
import fr.epf.plantes_connectees.data.ListPlantObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class EditPlanteActivity : AppCompatActivity() {
    var positionPlante = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_planteactivity)

        positionPlante = intent.getIntExtra("id", -1)
        var plante = ListPlantObject.getListPlant()?.get(positionPlante)

        val editdateTextView = findViewById<TextView>(R.id.date_edittext)
        val editNotesTextView = findViewById<TextView>(R.id.description_edittext)
        val libelleSpinner = findViewById<Spinner>(R.id.editspecies_spinner)

        val editPlantButton = findViewById<Button>(R.id.edit_plant_button)


        editPlantButton.setOnClickListener{
            if(editNotesTextView.text.toString() == ""){
                plante?.Description_plante = editNotesTextView.text.toString()
            }
            if(editdateTextView.text.toString() == ""){
                plante?.Date_plantation_plante = editdateTextView.text.toString()
            }
            plante?.Libelle_plante = libelleSpinner.selectedItem as String

            if (plante != null) {
                ListPlantObject.editPlantInDao(plante)
            }
            // TODO edit plante in API
            val intent = Intent(this, DetailsPlanteActivity::class.java)
            intent.putExtra("id", positionPlante)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_plante, menu)
        return super.onCreateOptionsMenu(menu, )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logo -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.back -> {
                val intent = Intent(this, DetailsPlanteActivity::class.java)
                intent.putExtra("id", positionPlante)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updatePlanteApi() {

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

        val params = HashMap<String?, String?>()
        val Adresse_Mac_plante = "test3"
        params["Libelle_plante"] = "MargueriteUpdate"
        params["Date_plantation_plante"] = "2022-05-05"
        params["Description_plante"] = "Ceci est un test de la requete POST pour update une plante depuis l application"
        params["Niveau_irrigation_plante"] = "3"
        params["Seuil_humidite_plante"] = "55"

        CoroutineScope(Dispatchers.IO).launch {
            serviceplantes.updatePlante(Adresse_Mac_plante,params)
        }
    }
}
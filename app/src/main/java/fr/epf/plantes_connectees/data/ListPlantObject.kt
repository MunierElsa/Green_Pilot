package fr.epf.plantes_connectees.data

import android.util.Log
import fr.epf.plantes_connectees.api.InfosPlantesService
import fr.epf.plantes_connectees.model.Plante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ListPlantObject {

    var plantesDao : PlantesDao? = null


    fun initializeDAO(planteDAOFromMainActivity : PlantesDao){
        plantesDao = planteDAOFromMainActivity
    }

    fun updateDao(listFromApi : List<Plante>){

        var listDAOBeforeSynchro = plantesDao?.getAllPlantes()

        for (planteAPI in listFromApi) {

            val planteFromDAO: Plante? =
                listDAOBeforeSynchro?.find { it.Adresse_Mac_plante == planteAPI.Adresse_Mac_plante }

            if(planteFromDAO == null){
               plantesDao?.insert(planteAPI)
            }
            else {
                plantesDao?.updatePlantes(planteAPI)
            }
        }
    }


    fun getListPlant() : List<Plante>? {
    return plantesDao?.getAllPlantes()
    }

    fun editPlantInDao(plante : Plante){
        plantesDao?.updatePlantes(plante)
        updatePlanteApi(plante)
    }
    fun deletePlantInDao(plante : Plante){
        plantesDao?.delete(plante)
        deletePlanteApi(plante)
    }

    private fun deletePlanteApi(plante : Plante) {

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

        val Adresse_Mac_plante = plante.Adresse_Mac_plante

        CoroutineScope(Dispatchers.IO).launch {
            serviceplantes.deletePlante(Adresse_Mac_plante)
        }
    }

    private fun updatePlanteApi(plante: Plante) {

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
        val Adresse_Mac_plante = plante.Adresse_Mac_plante
        params["Libelle_plante"] = plante.Libelle_plante
        params["Date_plantation_plante"] = plante.Date_plantation_plante
        params["Description_plante"] = plante.Description_plante
        params["Niveau_irrigation_plante"] = plante.Niveau_irrigation_plante
        params["Seuil_humidite_plante"] = plante.Seuil_humidite_plante

        CoroutineScope(Dispatchers.IO).launch {
            serviceplantes.updatePlante(Adresse_Mac_plante,params)
        }
    }

}
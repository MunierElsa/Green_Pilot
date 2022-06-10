package fr.epf.plantes_connectees.data

import android.util.Log
import com.google.gson.Gson
import fr.epf.plantes_connectees.model.Plante

object ListPlantObject {
    private var listPlantToTestApp : List<Plante> = listOf()

    var planteDao : PlanteDao? = null
    var mesureDao : MesureDao? = null


    fun initializeDAO(planteDAOFromMainActivity : PlanteDao, mesureDaoFromMainActivity : MesureDao){
        planteDao = planteDAOFromMainActivity
        mesureDao = mesureDaoFromMainActivity

    }


    fun updateDao(listFromApi : List<Plante>){
        //addMissingPlantesAndMesures(listFromApi)
        listPlantToTestApp = listFromApi
    }

    fun addMissingPlantesAndMesures(listFromApi : List<Plante>){
        var listDAOBeforeSynchro = planteDao?.getAllPlantes()
        var listMesureDaoBeforeSynchro = mesureDao?.getAllMesures()

        for (planteAPI in listFromApi) {
            val planteFromDAO: Plante? =
                listDAOBeforeSynchro?.find { it.Adresse_Mac_plante == planteAPI.Adresse_Mac_plante }
            if(planteFromDAO == null){
                planteDao?.insert(planteAPI)
            }
            else {
                planteDao?.updatePlantes(planteAPI)
            }

            for(mesure in planteAPI.Mesures){
                val mesureFromDao =
                    listMesureDaoBeforeSynchro?.find{it.Date_mesure == mesure.Date_mesure && it.Adresse_Mac_Plante == mesure.Adresse_Mac_Plante}

                if(mesureFromDao == null){
                    mesureDao?.insert(mesure)

                }

            }
        }
    }


    fun getListPlant() : List<Plante>? {
    return listPlantToTestApp
    //return planteDao?.getAllPlantes()
    }

    fun editList( listUpdated : List<Plante>){
        Log.d("Before Update", "$listPlantToTestApp")
        listPlantToTestApp = listUpdated

        Log.d("After Update", "$listPlantToTestApp")
    }


}
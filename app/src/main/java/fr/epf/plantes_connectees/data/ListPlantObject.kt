package fr.epf.plantes_connectees.data

import android.util.Log
import com.google.gson.Gson
import fr.epf.plantes_connectees.model.Mesure
import fr.epf.plantes_connectees.model.Plante

object ListPlantObject {
    private var listPlantToTestApp : List<Plante> = listOf()

    var planteDao : PlanteDao? = null
    var amountOfMesure : Int = 10


    fun initializeDAO(planteDAOFromMainActivity : PlanteDao){
        planteDao = planteDAOFromMainActivity

    }


    fun updateDao(listFromApi : List<Plante>){
        addMissingPlantesAndMesures(listFromApi)
        listPlantToTestApp = listFromApi
    }

    fun addMissingPlantesAndMesures(listFromApi : List<Plante>){

        var listDAOBeforeSynchro = planteDao?.getAllPlantes()

        for (planteAPI in listFromApi) {
            val planteFromDAO: Plante? =
                listDAOBeforeSynchro?.find { it.Adresse_Mac_plante == planteAPI.Adresse_Mac_plante }

            val listLastsMesureForPlant : MutableList<Mesure> = mutableListOf()

            val amountOfMesuresInPlante = planteFromDAO?.Mesures?.count()
            if (amountOfMesuresInPlante != null) {
                if(amountOfMesuresInPlante < amountOfMesure){
                    amountOfMesure = amountOfMesuresInPlante
                }
                    for(i in 1..amountOfMesure){
                        var planteMesure = planteFromDAO?.Mesures?.get(planteFromDAO.Mesures.count() - i)

                        if (planteMesure != null) {
                            listLastsMesureForPlant.add(planteMesure)
                        }
                    }
            }
            planteAPI?.Mesures?.clear()
            planteAPI?.Mesures = listLastsMesureForPlant


            if(planteFromDAO == null){
               planteDao?.insert(planteAPI)
            }
            else {
                planteDao?.updatePlantes(planteAPI)
            }


        }
    }


    fun getListPlant() : List<Plante>? {
    //return listPlantToTestApp
    return planteDao?.getAllPlantes()
    }

    fun editList( listUpdated : List<Plante>){
        Log.d("Before Update", "$listPlantToTestApp")
        listPlantToTestApp = listUpdated

        Log.d("After Update", "$listPlantToTestApp")
    }

    fun editPlantInDao(plante : Plante){

        planteDao?.updatePlantes(plante)
        //TODO edit API
    }
    fun deletePlantInDao(plante : Plante){
        planteDao?.delete(plante)
        //TODO delete plante in API
    }


}
package fr.epf.plantes_connectees.data

import android.util.Log
import fr.epf.plantes_connectees.model.Plante

object ListPlantObject {
    private var listPlantToTestApp : List<Plante> = listOf()

    fun createListPlant(){
        listPlantToTestApp = Plante.all(40)
    }

    fun getListPlant() : List<Plante>{
        return listPlantToTestApp
    }

    fun editList( listUpdated : List<Plante>){
        Log.d("Before Update", "$listPlantToTestApp")
        listPlantToTestApp = listUpdated

        Log.d("After Update", "$listPlantToTestApp")
    }
}
package fr.epf.plantes_connectees.api

import fr.epf.plantes_connectees.model.Irrigation
import retrofit2.http.GET
import retrofit2.http.Query

interface InfosIrrigationsService {

        @GET("irrigation.php")
        suspend fun getInfosIrrigations(@Query("Adresse_Mac_Plante")Adresse_Mac_Plante : String, @Query("n")n : Int): GetInfosIrrigationsResult

}
    data class GetInfosIrrigationsResult(val data: Irrigations)
    data class Irrigations(val irrigations: List<Irrigation>)

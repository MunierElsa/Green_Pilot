package fr.epf.plantes_connectees.api

import fr.epf.plantes_connectees.model.Mesure
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InfosMesuresService {

    @GET("plante.php")
    suspend fun getInfosMesures(@Query("Adresse_Mac_Plante")Adresse_Mac_Plante : String, @Query("n")n : Int): GetInfosMesuresResult

}
data class GetInfosMesuresResult(val data: Mesures)
data class Mesures(val mesures: List<Mesure>)
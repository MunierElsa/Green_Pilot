package fr.epf.plantes_connectees.api

import fr.epf.plantes_connectees.model.Mesure
import retrofit2.http.GET

interface InfosMesuresService {

    @GET("mesures.php")
    suspend fun getInfosMesures(): GetInfosMesuresResult

}
data class GetInfosMesuresResult(val data: Mesures)
data class Mesures(val mesures: List<Mesure>)
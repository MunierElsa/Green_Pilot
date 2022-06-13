package fr.epf.plantes_connectees.api

import fr.epf.plantes_connectees.model.Irrigation
import fr.epf.plantes_connectees.model.Mesure
import retrofit2.http.GET

interface InfosIrrigationsService {

        @GET("irrigation.php")
        suspend fun getInfosIrrigations(): GetInfosIrrigationsResult

}
    data class GetInfosIrrigationsResult(val data: Irrigations)
    data class Irrigations(val irrigations: List<Irrigation>)

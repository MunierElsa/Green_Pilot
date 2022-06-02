package fr.epf.plantes_connectees.api

import fr.epf.plantes_connectees.model.Plante
import retrofit2.http.GET

interface InfosPlantesService {

    @GET("plante.php")
    suspend fun getInfosPlantes(): GetInfosPlantesResult

}
data class GetInfosPlantesResult(val data: Plantes)
data class Plantes(val plantes: List<Plante>)
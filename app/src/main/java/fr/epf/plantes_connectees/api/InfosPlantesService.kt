package fr.epf.plantes_connectees.api

import fr.epf.plantes_connectees.model.Plante
import retrofit2.http.*

interface InfosPlantesService {

    @GET("plante.php")
    suspend fun getInfosPlantes(): GetInfosPlantesResult

    // Pour Update une plante
    @FormUrlEncoded
    @PUT("plante.php?")
    suspend fun updatePlante(@Query("id") Adresse_Mac_Plante:String, @FieldMap params:HashMap<String?, String?>)

    //POUR delete Plante
    @DELETE("plante.php?")
    suspend fun deletePlante(@Query("id") Adresse_Mac_Plante:String)

}
data class GetInfosPlantesResult(val data: Plantes)
data class Plantes(val plantes: List<Plante>)
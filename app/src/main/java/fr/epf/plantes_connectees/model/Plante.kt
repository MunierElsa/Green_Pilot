package fr.epf.plantes_connectees.model

import androidx.room.*
import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Math.random

enum class Species{
    Tomate, Fraise, Menthe, Basilic, Courgette
}

@Entity
data class Plante(
    @PrimaryKey val Adresse_Mac_plante: String,
    @ColumnInfo(name="Libelle_plante") var Libelle_plante: String,
    @ColumnInfo(name="Date_plantation_plante") var Date_plantation_plante: String,
    @ColumnInfo(name="Description_plante") var Description_plante: String,
    @ColumnInfo(name="Mesures") var Mesures: MutableList<Mesure>
) {
    public constructor() : this ("","","","", mutableListOf())

}

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<Mesure>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Mesure>::class.java).toList()
}
package fr.epf.plantes_connectees.model

import androidx.room.*
import com.google.gson.Gson

enum class Species{
    Tomate, Fraise, Menthe, Basilic, Courgette
}

@Entity
data class Plante(
    @PrimaryKey val Adresse_Mac_plante: String,
    @ColumnInfo(name="Libelle_plante") var Libelle_plante: String,
    @ColumnInfo(name="Date_plantation_plante") var Date_plantation_plante: String,
    @ColumnInfo(name="Description_plante") var Description_plante: String,
    @ColumnInfo(name="Niveau_irrigation_plante")val Niveau_irrigation_plante: String,
    @ColumnInfo(name="Seuil_humidite_plante")val Seuil_humidite_plante: String,
    @ColumnInfo(name="Mesures") var Mesures: MutableList<Mesure>
) {
    public constructor() : this ("","","","","","", mutableListOf())

}

class Converters {

    @TypeConverter
    fun listToJsonString(value: List<Mesure>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Mesure>::class.java).toList()
}
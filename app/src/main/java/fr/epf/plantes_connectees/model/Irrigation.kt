package fr.epf.plantes_connectees.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Irrigation(
    @ColumnInfo(name="Id_irrigation") val Id_irrigation: String,
    @ColumnInfo(name="Date_irrigation") val Date_irrigation: String,
    @ColumnInfo(name="Automatique_irrigation")  val Automatique_irrigation: String,
    @PrimaryKey val Adresse_Mac_Plante: String,
)  {
    constructor() : this ("","","","")

}
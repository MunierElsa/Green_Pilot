package fr.epf.plantes_connectees.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mesure(
    @ColumnInfo(name="Id_mesure") val Id_mesure: String,
    @ColumnInfo(name="Date_mesure") val Date_mesure: String,
    @ColumnInfo(name="Humidite_mesure")  val Humidite_mesure: String,
    @ColumnInfo(name="Temperature_mesure") val Temperature_mesure: String,
    @ColumnInfo(name="Luminosite_mesure") val Luminosite_mesure: String,
    @ColumnInfo(name="CO2_mesure") val CO2_mesure: String,
    @PrimaryKey val Adresse_Mac_Plante: String,
    )  {
    constructor() : this ("","","","","","","")

}
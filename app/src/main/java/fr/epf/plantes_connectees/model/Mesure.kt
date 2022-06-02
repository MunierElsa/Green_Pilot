package fr.epf.plantes_connectees.model

data class Mesure(
    val Id_mesure: String,
    val Date_mesure: String,
    val Humidite_mesure: String,
    val Temperature_mesure: String,
    val Luminosite_mesure: String,
    val CO2_mesure: String,
    val Adresse_Mac_Plante: String,

) {
    constructor() : this ("","","","","","","")
    companion object {
        fun all(nb : Int = 30) = (1..40).map {
            Mesure(
                "$it",
                "2022-05-$it",
                "$it",
                "$it",
                "$it",
                "$it",
                "adressemac$it",
            )
        }
    }
}
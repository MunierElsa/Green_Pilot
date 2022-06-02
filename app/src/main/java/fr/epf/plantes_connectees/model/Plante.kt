package fr.epf.plantes_connectees.model

import java.lang.Math.random

enum class Species{
    Tomate, Fraise, Menthe, Basilic, Courgette
}

data class Plante(
    val Adresse_Mac_plante: String,
    val Libelle_plante: String,
    val Date_plantation_plante: String,
    val Description_plante: String,
    var Mesures: MutableList<Mesure>
) {
    constructor() : this ("","","","",list)
    companion object {
        val list: MutableList<Mesure> = mutableListOf()
        fun all(nb : Int = 30) = (1..40).map {
            val random = (1 until 6).random()
            Plante(
                "adressemac$it",
                "",
                //if (random == 1) Species.Tomate else if (random == 2) Species.Fraise else if(random == 3) Species.Menthe else if(random == 4) Species.Basilic else Species.Courgette,
                "2022-05-$it",
                "description$it",
                list
            )

        }
    }
}

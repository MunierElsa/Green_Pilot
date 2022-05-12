package fr.epf.plantes_connectees.model

import java.lang.Math.random

enum class Species{
    Tomate, Fraise, Menthe, Basilic, Courgette
}

data class Plante(
    var id: Int,
    var name: String,
    var species: Species,
    var description: String,
    var date: String,
) {
    constructor() : this (0,"", Species.Tomate,"","")
    companion object {

        fun all(nb : Int = 30) = (1..40).map {
            val random = (1 until 6).random()
            Plante(
                nb,
                "nom$it",
                if (random == 1) Species.Tomate else if (random == 2) Species.Fraise else if(random == 3) Species.Menthe else if(random == 4) Species.Basilic else Species.Courgette,
                "description$it",
                "2022-05-$it",
            )

        }
    }
}

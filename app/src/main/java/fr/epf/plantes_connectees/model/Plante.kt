package fr.epf.plantes_connectees.model


data class Plante(
    var id: Int,
    var name: String,
    var species: String,
    var description: String,
    var date: String,
) {
    constructor() : this (0,"","","","")
    companion object {

        fun all(nb : Int = 30) = (1..40).map {
            Plante(
                nb,
                "nom$it",
                "tomate",
                "description$it",
                "2022-05-$it",
            )

        }
    }
}

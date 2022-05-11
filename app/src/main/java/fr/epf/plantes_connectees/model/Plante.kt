package fr.epf.plantes_connectees.model


data class Plante(
    val id: Int,
    var name: String,
    val species: String,
    val description: String,
    val date: String,
) {
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

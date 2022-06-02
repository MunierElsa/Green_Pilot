package fr.epf.plantes_connectees

import android.widget.ImageView
import fr.epf.plantes_connectees.model.Plante
import fr.epf.plantes_connectees.model.Species

fun ImageView.setPlante(plante : Plante) {
    this.setImageResource(
    when(plante.Libelle_plante){
        "JonquilleTest3"-> R.drawable.basilic
        /*Species.Tomate-> R.drawable.tomate
        Species.Basilic-> R.drawable.basilic
        Species.Fraise-> R.drawable.fraise
        Species.Courgette-> R.drawable.courgette
        Species.Menthe-> R.drawable.menthe*/
        else -> {R.drawable.tomate}
    }
    )
}
package fr.epf.plantes_connectees

import android.widget.ImageView
import fr.epf.plantes_connectees.model.Plante
import fr.epf.plantes_connectees.model.Species

fun ImageView.setPlante(plante : Plante) {
    this.setImageResource(
    when(plante.Libelle_plante){
        "Tomate"-> R.drawable.tomate
        "Basilic"-> R.drawable.basilic
        "Fraise"-> R.drawable.fraise
        "Courgette"-> R.drawable.courgette
        "Menthe"-> R.drawable.menthe
        "Persil"-> R.drawable.persil
        else -> {R.drawable.ic_baseline_question_mark_24}
    }
    )
}

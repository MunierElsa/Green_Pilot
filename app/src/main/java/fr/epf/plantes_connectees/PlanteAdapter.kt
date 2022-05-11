package fr.epf.plantes_connectees

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.plantes_connectees.model.Plante

class PlanteAdapter(val plantes: List<Plante>) : RecyclerView.Adapter<PlanteAdapter.PlanteViewHolder>() {

    class PlanteViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val clientView = inflater.inflate(R.layout.adapter_plante,parent,false)
        return PlanteViewHolder(clientView)
    }

    override fun onBindViewHolder(holder: PlanteViewHolder, position: Int) {
        val plante = plantes[position]

        val textview = holder.view.findViewById<TextView>(R.id.adapter_plante_name_textView)
        textview.setTextColor(Color.parseColor("#FF000000"));
        val textview2 = holder.view.findViewById<TextView>(R.id.adapter_plante_values_textView)
        textview2.setTextColor(Color.parseColor("#FF000000"));
        val planteImageView = holder.view.findViewById<ImageView>(R.id.adapter_plante_imageview)
        textview.text = plante.getNom()
        textview2.text = plante.getValeurs()

        planteImageView.setPlante(plante)
    }

    override fun getItemCount() = plantes.size
}

//Rajouter des fonctions à des classes dans des bibliothèques que l'on a pas créé
fun Plante.getNom() : String {
    return "${name} ${description}"
}

fun Plante.getValeurs() : String {
    return "Luminosité, Température ..."
}
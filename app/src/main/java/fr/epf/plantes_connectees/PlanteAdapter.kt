package fr.epf.plantes_connectees

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.epf.plantes_connectees.model.Mesure
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

        holder.view.setOnClickListener{
            val context = it.context
            val intent = Intent(it.context, DetailsPlanteActivity::class.java)
            intent.putExtra("id", position)
            context.startActivity(intent)
        }

        val textview = holder.view.findViewById<TextView>(R.id.adapter_plante_name_textView)
        textview.setTextColor(Color.parseColor("#FF000000"));
        val textview2 = holder.view.findViewById<TextView>(R.id.adapter_plante_firstvalue_textView)
        textview2.setTextColor(Color.parseColor("#FF000000"));
        val textview3 = holder.view.findViewById<TextView>(R.id.adapter_plante_secondvalue_textView)
        textview3.setTextColor(Color.parseColor("#FF000000"));
        val planteImageView = holder.view.findViewById<ImageView>(R.id.adapter_plante_imageview)
        textview.text = plante.getNom()
        textview2.text = plante.getFirstValue()
        textview3.text = plante.getSecondValue()

        planteImageView.setPlante(plante)
    }

    override fun getItemCount() = plantes.size
}

//Rajouter des fonctions à des classes dans des bibliothèques que l'on a pas créé
fun Plante.getNom() : String {
    return "${Adresse_Mac_plante} \n \n "
}

fun Plante.getFirstValue() : String {
    return "${Description_plante}"
}
fun Plante.getSecondValue() : String {
    return "Luminosité : ${Mesures.last().Luminosite_mesure} \n" +
            "Temperature : ${Mesures.last().Temperature_mesure} °C \n" +
            "Qualité de l'air : ${niveauCo2(Mesures.last().CO2_mesure)}\n" +
            "Niveau d'humidité : ${Mesures.last().Humidite_mesure} "
}

fun niveauCo2(tauxCO2 : String): String{
    return when(tauxCO2){
        "0"-> "très bonne qualité"
        "1"-> "bonne qualité"
        "2"-> "air pollué"
        "3"-> "air très pollué"
        else -> "Taux inconnu"
    }
}
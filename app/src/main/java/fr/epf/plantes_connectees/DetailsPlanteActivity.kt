package fr.epf.plantes_connectees

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.epf.plantes_connectees.data.ListPlantObject
import fr.epf.plantes_connectees.model.Plante
import org.eclipse.paho.android.service.MqttAndroidClient
import kotlin.math.roundToInt

class DetailsPlanteActivity : AppCompatActivity() {

    var positionPlante = -1
    var plante = Plante()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_plante)

        positionPlante =intent.getIntExtra("id", -1)
        plante = ListPlantObject.getListPlant()?.get(positionPlante)!!

        val nameTextView = findViewById<TextView>(R.id.name_textView)
        nameTextView.text = plante?.Adresse_Mac_plante+ "\n"
        val dateTextView = findViewById<TextView>(R.id.date_textView)
        dateTextView.text = plante?.Date_plantation_plante+ "\n"
        val descriptionTextView = findViewById<TextView>(R.id.description_textView)
        descriptionTextView.text = plante?.Description_plante+"\n"

        val humiditeTextView = findViewById<TextView>(R.id.humidite_textView)
        val temperatureTextView = findViewById<TextView>(R.id.temperature_textView)
        val luminositeTextView = findViewById<TextView>(R.id.luminosite_textView)
        val co2TextView = findViewById<TextView>(R.id.tauxCO2_textView)

        var moyenneHumiditĂ© = 0.0
        var moyenneTemperature = 0.0
        var moyenneLuminosite = 0.0
        var moyenneCO2 = 0.0
        var amountOfMesures = plante?.Mesures.count()
        for(mesure in plante?.Mesures){
            moyenneHumiditĂ© += mesure.Humidite_mesure.toFloat()
            moyenneTemperature += mesure.Temperature_mesure.toFloat()
            moyenneLuminosite += mesure.Luminosite_mesure.toFloat()
            moyenneCO2 += mesure.CO2_mesure.toFloat()
        }
        moyenneHumiditĂ© = moyenneHumiditĂ©/amountOfMesures
        moyenneTemperature = moyenneTemperature/amountOfMesures
        moyenneLuminosite = moyenneLuminosite/amountOfMesures
        moyenneCO2 = moyenneCO2/amountOfMesures
        humiditeTextView.text = "DerniĂ¨re mesure : ${plante?.Mesures.last().Humidite_mesure} %\nMoyenne : ${moyenneHumiditĂ©.roundToInt()}%\n"
        temperatureTextView.text = "DerniĂ¨re mesure : ${plante?.Mesures.last().Temperature_mesure} Â°C\nMoyenne : ${moyenneTemperature.roundToInt()}Â°C\n"
        luminositeTextView.text = "DerniĂ¨re mesure : ${plante?.Mesures.last().Luminosite_mesure} %\nMoyenne : ${moyenneLuminosite.roundToInt()}%\n"
        co2TextView.text = "DerniĂ¨re mesure : ${niveauCo2(plante?.Mesures.last().CO2_mesure.toInt())}\nMoyenne : ${niveauCo2(moyenneCO2.roundToInt())}"




        val planteImageView = findViewById<ImageView>(R.id.adapter_plante_imageview)


        if (plante != null) {
            planteImageView.setPlante(plante)
        }


    }
    fun niveauCo2(tauxCO2 : Int): String{
        return when(tauxCO2){
            0-> "trĂ¨s bonne qualitĂ©"
            1-> "bonne qualitĂ©"
            2-> "air polluĂ©"
            3-> "air trĂ¨s polluĂ©"
            else -> "Taux inconnu"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_plante, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.edit-> {
                val intent = Intent(this, EditPlanteActivity::class.java)
                intent.putExtra("id", positionPlante)
                startActivity(intent)
            }
            R.id.Delete-> {
                ListPlantObject.deletePlantInDao(plante)
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.Arroser-> {
                val intent = Intent(this, ArroserPlanteActivity::class.java)
                intent.putExtra("adresse_mac",plante.Adresse_Mac_plante)
                startActivity(intent)
            }
            R.id.logo->{
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
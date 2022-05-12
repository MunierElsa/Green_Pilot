package fr.epf.plantes_connectees

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.epf.plantes_connectees.data.ListPlantObject

class DetailsPlanteActivity : AppCompatActivity() {

    var positionPlante = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_plante)

        positionPlante =intent.getIntExtra("id", -1)
        var plante = ListPlantObject.getListPlant()[positionPlante]

        val nameTextView = findViewById<TextView>(R.id.name_textView)
        nameTextView.text = plante.name+ "\n"
        val speciesTextView = findViewById<TextView>(R.id.species_textView)
        speciesTextView.text = plante.species.toString()+ "\n"
        val dateTextView = findViewById<TextView>(R.id.date_textView)
        dateTextView.text = plante.date+ "\n"
        val descriptionTextView = findViewById<TextView>(R.id.description_textView)
        descriptionTextView.text = plante.description

        val planteImageView = findViewById<ImageView>(R.id.adapter_plante_imageview)


        planteImageView.setPlante(plante)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_plante, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.Modifier-> {
                startActivity(Intent(this, EditPlanteActivity::class.java))
            }
            R.id.Arroser-> {
                //startActivity(Intent(this, ArroserPlantActivity::class.java))
            }
            R.id.home->{
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
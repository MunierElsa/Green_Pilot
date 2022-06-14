package fr.epf.plantes_connectees

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.epf.plantes_connectees.data.ListPlantObject
import fr.epf.plantes_connectees.model.Plante

class ArroserPlanteActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arroser_plante)
        val levelSpinner = findViewById<Spinner>(R.id.editlevel_spinner)
        val irrigation_id = findViewById<Button>(R.id.irrigation_id)

        irrigation_id.setOnClickListener {
            //Mettre le message à envoyé à Pascal
            //levelSpinner.selectedItem as String
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_arroser_plante, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.logo->{
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
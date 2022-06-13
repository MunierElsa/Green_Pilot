package fr.epf.plantes_connectees

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import fr.epf.plantes_connectees.data.ListPlantObject
import fr.epf.plantes_connectees.model.Mesure
import fr.epf.plantes_connectees.model.Plante
import fr.epf.plantes_connectees.model.Species

class AddPlanteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addplant)

        val nameEditText: EditText = findViewById<EditText>(R.id.name_edittext)
        val speciesEditText: EditText = findViewById(R.id.species_edittext)
        val dateEditText: EditText = findViewById(R.id.date_edittext)
        val descriptionEditText: EditText = findViewById(R.id.description_edittext)
        val speciesSpinner = findViewById<Spinner>(R.id.species_spinner)

        val addButton = findViewById<Button>(R.id.add_plant_button)

        addButton.setOnClickListener {
            // Ajouter la plante à la liste
            var list : MutableList<Plante> = mutableListOf()
            list = ListPlantObject.getListPlant() as MutableList<Plante>
            var listmesures : MutableList<Mesure> = mutableListOf()

            var plant =  Plante("","", "", "","","",listmesures)

            //var planteToAdd = Plante()
            //planteToAdd.id = list.size
            //val name = nameEditText.text as String
            /*planteToAdd.name = nameEditText.text as String
            planteToAdd.species = speciesSpinner.selectedItem as String
            planteToAdd.description = descriptionEditText.text as String
            planteToAdd.date = dateEditText.text as String*/


            list.add(plant)
            ListPlantObject.editList(list)
            startActivity(Intent(this, MainActivity::class.java))
            }


        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_plante, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

package fr.epf.plantes_connectees

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import fr.epf.plantes_connectees.data.ListPlantObject
import fr.epf.plantes_connectees.model.Plante

class AddPlantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addplant)

        ListPlantObject.createListPlant()
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


            var plant =  Plante(2,"anne","tomate", "oui", "2424")

            //var planteToAdd = Plante(list.size, nameEditText.text as String,
              //  speciesSpinner.selectedItem as String, descriptionEditText.text as String,
                //dateEditText.text as String)


            list.add(plant)
            ListPlantObject.editList(list)

            Log.d("Plante", "Nom : ${nameEditText.text}")
            Log.d("Plante", "Espèce : ${speciesEditText.text}")
            Log.d("Plante", "Espèce spinner : ${speciesSpinner.selectedItem}")
            Log.d("Plante", "Date plantation : ${dateEditText.text}")
            Log.d("Plante", "Description : ${descriptionEditText.text}")
        }

    }

}
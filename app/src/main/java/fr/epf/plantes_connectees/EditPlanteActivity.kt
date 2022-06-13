package fr.epf.plantes_connectees

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import fr.epf.plantes_connectees.api.InfosPlantesService
import fr.epf.plantes_connectees.data.ListPlantObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class EditPlanteActivity : AppCompatActivity() {
    var positionPlante = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_planteactivity)

        positionPlante = intent.getIntExtra("id", -1)
        var plante = ListPlantObject.getListPlant()?.get(positionPlante)

        //val editdateTextView = findViewById<TextView>(R.id.date_edittext)
        //val editNotesTextView = findViewById<TextView>(R.id.description_edittext)
        val libelleSpinner = findViewById<Spinner>(R.id.editspecies_spinner)

        val editPlantButton = findViewById<Button>(R.id.edit_plant_button)


        editPlantButton.setOnClickListener {
            /*if (editNotesTextView.text.toString() == "") {
                plante?.Description_plante = editNotesTextView.text.toString()
            }
            if (editdateTextView.text.toString() == "") {
                plante?.Date_plantation_plante = editdateTextView.text.toString()
            }*/
            plante?.Libelle_plante = libelleSpinner.selectedItem as String

            if (plante != null) {
                ListPlantObject.editPlantInDao(plante)
            }
            val intent = Intent(this, DetailsPlanteActivity::class.java)
            intent.putExtra("id", positionPlante)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_plante, menu)
        return super.onCreateOptionsMenu(menu,)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logo -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.back -> {
                val intent = Intent(this, DetailsPlanteActivity::class.java)
                intent.putExtra("id", positionPlante)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
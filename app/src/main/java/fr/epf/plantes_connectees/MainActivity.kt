package fr.epf.plantes_connectees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.plantes_connectees.data.ListPlantObject
import fr.epf.plantes_connectees.model.Plante

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //On créé une liste que l'on va réutiliser le long du projet
        ListPlantObject.createListPlant()

        val listPlantesRecyclerview = findViewById<RecyclerView>(R.id.list_plantes_recyclerview)

        listPlantesRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        val plants = ListPlantObject.getListPlant()
        listPlantesRecyclerview.adapter = PlanteAdapter(plants)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
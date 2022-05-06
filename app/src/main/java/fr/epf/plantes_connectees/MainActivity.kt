package fr.epf.plantes_connectees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.plantes_connectees.model.Plante

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listPlantesRecyclerview = findViewById<RecyclerView>(R.id.list_plantes_recyclerview)

        listPlantesRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        val clients = Plante.all(40)
        listPlantesRecyclerview.adapter = PlanteAdapter(clients)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
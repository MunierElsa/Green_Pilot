package fr.epf.plantes_connectees.data
import androidx.room.*
import fr.epf.plantes_connectees.model.Irrigation
import fr.epf.plantes_connectees.model.Mesure
import fr.epf.plantes_connectees.model.Plante

@Dao
interface PlanteDao {
    @Query("SELECT * FROM plante")
    fun getAllPlantes(): List<Plante>

    @Insert
    fun insert(vararg plante: Plante)

    @Delete
    fun delete(plante: Plante)

    @Update
    fun updatePlantes(vararg plantes: Plante)
}

@Dao
interface MesureDao {
    @Query("SELECT * FROM mesure")
    fun getAllMesures(): List<Mesure>

    /*@Insert
    fun insert(vararg mesure: Mesure)

    @Delete
    fun delete(mesure: Mesure)

    @Update
    fun updateMesures(vararg mesures: Mesure)*/
}

@Dao
interface IrrigationDao {
    @Query("SELECT * FROM irrigation")
    fun getAllIrrigations(): List<Irrigation>
/*
    @Insert
    fun insert(vararg mesure: Mesure)

    @Delete
    fun delete(mesure: Mesure)

    @Update
    fun updateMesures(vararg mesures: Mesure)*/
}

package fr.epf.plantes_connectees.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.epf.plantes_connectees.model.Converters
import fr.epf.plantes_connectees.model.Mesure
import fr.epf.plantes_connectees.model.Plante

@Database(entities = [Plante::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabasePlante : RoomDatabase() {
    abstract fun planteDao(): PlanteDao
}

@Database(entities = [Mesure::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabaseMesure : RoomDatabase() {
    abstract fun mesureDao(): MesureDao
}
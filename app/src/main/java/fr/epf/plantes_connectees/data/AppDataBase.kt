package fr.epf.plantes_connectees.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.epf.plantes_connectees.model.*

@Database(entities = [Plante::class], version = 2)
@TypeConverters(Converters::class,Converters2::class)
abstract class AppDatabasePlantes : RoomDatabase() {
    abstract fun planteDao(): PlantesDao
}

@Database(entities = [Mesure::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabaseMesures : RoomDatabase() {
    abstract fun mesureDao(): MesuresDao
}

@Database(entities = [Irrigation::class], version = 1)
@TypeConverters(Converters2::class)
abstract class AppDatabaseIrrigation : RoomDatabase() {
    abstract fun irrigationDao(): IrrigationDao
}
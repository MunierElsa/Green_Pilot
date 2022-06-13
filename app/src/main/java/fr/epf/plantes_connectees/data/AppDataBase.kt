package fr.epf.plantes_connectees.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.epf.plantes_connectees.model.*

@Database(entities = [Plante::class], version = 1)
@TypeConverters(Converters::class,Converters2::class)
abstract class AppDatabasePlante : RoomDatabase() {
    abstract fun planteDao(): PlanteDao
}

@Database(entities = [Mesure::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabaseMesure : RoomDatabase() {
    abstract fun mesureDao(): MesureDao
}

@Database(entities = [Irrigation::class], version = 1)
@TypeConverters(Converters2::class)
abstract class AppDatabaseIrrigation : RoomDatabase() {
    abstract fun irrigationDao(): IrrigationDao
}
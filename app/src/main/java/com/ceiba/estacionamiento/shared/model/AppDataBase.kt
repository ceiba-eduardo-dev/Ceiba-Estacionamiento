package com.ceiba.estacionamiento.shared.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ceiba.estacionamiento.shared.model.BuildConfig.DataBaseName
import com.ceiba.estacionamiento.vehicles.model.dataaccess.daos.VehicleDao
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity

@Database(
    entities = [VehicleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DataBaseName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
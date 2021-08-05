package com.ceiba.infrestructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ceiba.infrestructure.database.dao.CarDao
import com.ceiba.infrestructure.database.dao.MotorcycleDao
import com.ceiba.infrestructure.database.entity.CarEntity
import com.ceiba.infrestructure.database.entity.MotorcycleEntity

@Database(
    entities = [CarEntity::class, MotorcycleEntity::class],
    version = 1, exportSchema = false
)
abstract class ParkingDatabase : RoomDatabase() {
    abstract fun motorcycleDao(): MotorcycleDao
    abstract fun carDao(): CarDao

    companion object {

        @Volatile
        private var instance: ParkingDatabase? = null

        fun get(context: Context): ParkingDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ParkingDatabase {
            return Room.databaseBuilder(context, ParkingDatabase::class.java, "prueba-parking").build()
        }
    }
}
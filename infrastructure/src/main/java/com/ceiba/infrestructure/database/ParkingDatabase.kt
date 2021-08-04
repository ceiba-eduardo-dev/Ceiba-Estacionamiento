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
        private var INSTANCE: ParkingDatabase? = null

        @Synchronized
        fun get(context: Context): ParkingDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ParkingDatabase::class.java, "parking-v1"
                ).build()
            }
            return INSTANCE!!
        }
    }
}
package com.ceiba.infrestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ceiba.infrestructure.database.entity.MotorcycleEntity

@Dao
interface MotorcycleDao {
    @Insert
    fun saveMotorcycle(MotorcycleEntity: MotorcycleEntity?)

    @Query("SELECT * FROM Motorcycle_entity")
    fun getAllMotorcycles(): List<MotorcycleEntity?>?

    @Query("DELETE FROM Motorcycle_entity WHERE id = :id")
    fun deleteMotorcycle(id: Int)

    @Query("SELECT COUNT(*) FROM Motorcycle_entity")
    fun getCountTotalMotorcycles(): Byte
}
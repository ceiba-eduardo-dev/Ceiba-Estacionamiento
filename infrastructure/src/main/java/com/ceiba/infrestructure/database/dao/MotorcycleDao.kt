package com.ceiba.infrestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ceiba.infrestructure.database.entity.MotorcycleEntity

@Dao
interface MotorcycleDao {
    @Insert
    fun saveMotorcycle(motorcycleEntity: MotorcycleEntity?)

    @Query("SELECT * FROM motorcycle_entity")
    fun getAllMotorcycles(): List<MotorcycleEntity>

    @Query("DELETE FROM motorcycle_entity WHERE id = :id")
    fun deleteMotorcycle(id: Int)

    @Query("SELECT COUNT(*) FROM motorcycle_entity")
    fun getCountTotalMotorcycles(): Int

    @Query("SELECT * FROM motorcycle_entity WHERE licensePlate LIKE :licensePlate")
    fun getByLicensePlate(licensePlate: String): MotorcycleEntity
}
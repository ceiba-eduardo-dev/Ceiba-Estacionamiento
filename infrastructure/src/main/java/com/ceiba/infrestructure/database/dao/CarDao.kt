package com.ceiba.infrestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ceiba.infrestructure.database.entity.CarEntity

@Dao
interface CarDao {
    @Insert
    fun saveCar(carEntity: CarEntity?)

    @Query("SELECT * FROM car_entity")
    fun getAllCars(): List<CarEntity?>?

    @Query("DELETE FROM car_entity WHERE id = :id")
    fun deleteCar(id: Int)

    @Query("SELECT COUNT(*) FROM car_entity")
    fun getCountTotalCars(): Byte
}
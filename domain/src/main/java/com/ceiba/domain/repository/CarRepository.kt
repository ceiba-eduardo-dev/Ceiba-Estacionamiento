package com.ceiba.domain.repository

import com.ceiba.domain.model.Car

interface CarRepository {
    fun getAll(): List<Car>
    fun getCountTotalCars(): Int
    fun saveCar(car: Car)
    fun deleteCar(id: Int)
    fun validateAccessCarForDay(licensePlate: String, string: String): Boolean
}
package com.ceiba.domain.model.repository

import com.ceiba.domain.model.entity.Car

interface RepositoryCar {
    fun getAllCars(): List<Car>
    fun getCountTotalCars(): Int
    fun saveCar(car: Car)
    fun deleteCar(id: Int)
    fun validateAccessCarForDay(car: Car, day: Any): Boolean
}
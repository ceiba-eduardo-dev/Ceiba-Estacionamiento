package com.ceiba.domain.repository

import com.ceiba.domain.model.Car

interface CarRepository {
    fun getAll(): List<Car>
    fun getCount(): Int
    fun getByLicensePlate(licensePlate: String): Car?
    fun save(car: Car)
    fun delete(car: Car)
}
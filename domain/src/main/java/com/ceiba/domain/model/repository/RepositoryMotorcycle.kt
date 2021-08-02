package com.ceiba.domain.model.repository

import com.ceiba.domain.model.entity.Car

interface RepositoryMotorcycle {
    fun getAllMotorcycles(): List<Car>
    fun getCountTotalMotorcycles(): Int
    fun saveMotorcycle(car: Car)
    fun deleteMotorcycle(id: Int)
}
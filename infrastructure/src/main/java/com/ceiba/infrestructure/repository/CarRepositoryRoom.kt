package com.ceiba.infrestructure.repository

import com.ceiba.domain.model.entity.Car
import com.ceiba.domain.model.repository.RepositoryCar

class CarRepositoryRoom : RepositoryCar {
    override fun getAllCars(): List<Car> {
        return arrayListOf()
    }

    override fun getCountTotalCars(): Int {
        return 0
    }

    override fun saveCar(car: Car) {
    }

    override fun deleteCar(id: Int) {
    }

    override fun validateAccessCarForDay(car: Car, day: Any): Boolean {
        return true
    }
}
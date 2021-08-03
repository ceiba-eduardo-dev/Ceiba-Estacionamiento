package com.ceiba.infrestructure.repository

import com.ceiba.domain.model.Car
import com.ceiba.domain.repository.CarRepository

class CarRepositoryRoom : CarRepository {
    override fun getAll(): List<Car> {
        return arrayListOf()
    }

    override fun getCountTotalCars(): Int {
        return 0
    }

    override fun saveCar(car: Car) {
    }

    override fun deleteCar(id: Int) {
    }

}
package com.ceiba.domain.service

import com.ceiba.domain.exception.ParkingCarServiceException
import com.ceiba.domain.model.Car
import com.ceiba.domain.repository.CarRepository

class ParkingCarService(
    private val carRepository: CarRepository,
) {
    fun saveCar(car: Car, day: String) {
        if (carRepository.validateAccessCarForDay(car.getLicensePlate, day)) {
            carRepository.saveCar(car)
        } else {
            return throw ParkingCarServiceException()
        }
    }

}
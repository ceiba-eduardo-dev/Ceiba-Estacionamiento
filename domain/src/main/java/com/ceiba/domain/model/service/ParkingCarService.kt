package com.ceiba.domain.model.service

import com.ceiba.domain.exception.ParkingCarServiceException
import com.ceiba.domain.model.entity.Car
import com.ceiba.domain.model.repository.RepositoryCar

class ParkingCarService(
    private val repositoryCar: RepositoryCar,
) {
    fun saveCar(car: Car, day: Any) {
        if (repositoryCar.validateAccessCarForDay(car, day)) {
            repositoryCar.saveCar(car)
        } else {
            return throw ParkingCarServiceException()
        }
    }

}
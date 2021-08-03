package com.ceiba.domain.service

import com.ceiba.domain.exception.ParkingCarServiceException
import com.ceiba.domain.model.Car
import com.ceiba.domain.repository.CarRepository

class ParkingCarService(
    private val carRepository: CarRepository,
) {
    companion object {
        const val monday: String = "LUNES"
        private const val regularExpressionInitLetterA = "[aA]"
    }

    fun validateAccessCarForDay(car: Car, day: String): Boolean {
        val regex = regularExpressionInitLetterA.toRegex()
        val startWhitA = regex.containsMatchIn(car.getLicensePlate)
        return if (startWhitA && day == monday) {
            throw ParkingCarServiceException()
        } else {
            true
        }

    }

}
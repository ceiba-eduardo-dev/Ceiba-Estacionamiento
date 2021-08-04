package com.ceiba.domain.service

import com.ceiba.domain.exception.ParkingCarServiceException
import com.ceiba.domain.exception.VehicleAlreadyExistException
import com.ceiba.domain.exception.VehicleDeleteException
import com.ceiba.domain.model.Car
import com.ceiba.domain.repository.CarRepository
import java.lang.Exception
import javax.inject.Inject

class ParkingCarService @Inject constructor(
    private val carRepository: CarRepository
) : VehicleService() {
    companion object {
        const val monday: String = "LUNES"
        private const val regularExpressionInitLetterA = "[aA]"
        const val priceHourCar = 1000
        const val priceDayCar = 8000
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

    fun getAll(): List<Car>? {
        return carRepository.getAll()
    }

    fun getCount(): Int? {
        var count = carRepository.getCount()
        return if (count != null) {
            carRepository.getCount()
        } else {
            0
        }
    }

    fun getCarByLicensePlate(licensePlate: String): Car? {
        return carRepository.getByLicensePlate(licensePlate)
    }

    fun saveCar(car: Car, day: String) {
        if (validateAccessCarForDay(car, day)) {
            if (carRepository.getByLicensePlate(car.getLicensePlate) == null) {
                carRepository.save(car)
            } else {
                throw VehicleAlreadyExistException()
            }
        }
    }

    fun deleteCar(car: Car) {
        try {
            carRepository.delete(car)
        } catch (ex: Exception) {
            throw  VehicleDeleteException()
        }
    }

    fun calculatePriceByCar(hours: Int): Int {
        return calculatePriceForHours(hours, priceDayCar, priceHourCar)
    }

}
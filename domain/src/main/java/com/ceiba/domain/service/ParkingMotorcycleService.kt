package com.ceiba.domain.service

import com.ceiba.domain.exception.VehicleAlreadyExistException
import com.ceiba.domain.exception.VehicleDeleteException
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.repository.MotorcycleRepository
import javax.inject.Inject

class ParkingMotorcycleService (
    private val motorcycleRepository: MotorcycleRepository
) : VehicleService() {
    companion object {
        const val maxCylinderCapacity = 500
        const val priceAggregate = 2000
        const val priceHourMotorcycle = 500
        const val priceDayMotorcycle = 4000
    }

    fun getAll(): List<Motorcycle>? {
        return motorcycleRepository.getAll()
    }

    fun getCount(): Int? {
        val count = motorcycleRepository.getCount()
        if (count != null)
            return count
        return 0
    }

    fun getMotorcycleByLicensePlate(licensePlate: String): Motorcycle {
        return motorcycleRepository.getByLicensePlate(licensePlate)
    }

    fun saveMotorcycle(car: Motorcycle) {
        if (getMotorcycleByLicensePlate(car.getLicensePlate) == null) {
            motorcycleRepository.save(car)
        } else {
            throw VehicleAlreadyExistException()
        }
    }

    fun deleteMotorcycle(motorcycle: Motorcycle) {
        try {
            motorcycleRepository.delete(motorcycle)
        } catch (e: Exception) {
            throw VehicleDeleteException()
        }
    }

    fun getPriceByCylinderCapacity(motorcycle: Motorcycle): Int {
        if (motorcycle.getCylinderCapacity() > maxCylinderCapacity)
            return priceAggregate
        return 0
    }

    fun calculatePriceByMotorcycle(hours: Int): Int {
        return calculatePriceForHours(hours, priceDayMotorcycle, priceHourMotorcycle)
    }

}
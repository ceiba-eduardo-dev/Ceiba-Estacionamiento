package com.ceiba.infrestructure.anticorruption

import com.ceiba.domain.model.Car
import com.ceiba.domain.model.parking.CalendarParking
import com.ceiba.infrestructure.database.entity.CarEntity
import java.util.*

abstract class CarTranslator {
    companion object {
        /**FORMAT dd-MM-yyyy hh:mm:ss*/
        fun fromDomainToEntity(car: Car): CarEntity {
            val date: Date = car.getDateEntry.time
            return CarEntity(car.getLicensePlate, date.toString())
        }

        fun fromEntityToDomain(car: CarEntity): Car =
            Car(car.licensePlate!!, CalendarParking(car.dateEntry).getCalendar())

        fun fromCarsEntityToCarsDomain(cars: List<CarEntity>): List<Car> =
            cars.map { fromEntityToDomain(it) }

    }

}
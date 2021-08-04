package com.ceiba.infrestructure.anticorruption

import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.parking.CalendarParking
import com.ceiba.infrestructure.database.entity.MotorcycleEntity

class MotorcycleTranslator() {
    companion object {
        fun fromDomainToEntity(motorcycle: Motorcycle): MotorcycleEntity {
            return MotorcycleEntity(
                motorcycle.getLicensePlate,
                motorcycle.getCylinderCapacity(),
                motorcycle.getDateEntry.time.toString()
            )
        }

        fun fromEntityToDomain(motorcycle: MotorcycleEntity): Motorcycle {
            return Motorcycle(
                motorcycle.licensePlate,
                motorcycle.cylinderCapacity,
                CalendarParking(motorcycle.dateEntry).getCalendar(),
            )
        }

        fun fromListEntityToListDomain(motorcycles: List<MotorcycleEntity>): List<Motorcycle> =
            motorcycles.map { fromEntityToDomain(it) }
    }
}
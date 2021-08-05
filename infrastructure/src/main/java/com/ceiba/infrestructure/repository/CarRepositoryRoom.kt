package com.ceiba.infrestructure.repository

import android.content.Context
import androidx.room.Room
import com.ceiba.domain.model.Car
import com.ceiba.domain.repository.CarRepository
import com.ceiba.infrestructure.anticorruption.CarTranslator
import com.ceiba.infrestructure.database.ParkingDatabase


class CarRepositoryRoom :
    CarRepository {
    var database: ParkingDatabase

    constructor(context: Context) {
        database = ParkingDatabase.get(context)
    }

    override fun save(car: Car) =
        database.carDao().saveCar(CarTranslator.fromDomainToEntity(car))

    override fun delete(car: Car) =
        database.carDao().deleteCar(CarTranslator.fromDomainToEntity(car).id)

    override fun getCount(): Int = database.carDao().getCountTotalCars()

    override fun getByLicensePlate(licensePlate: String): Car {
        return CarTranslator.fromEntityToDomain(database.carDao().getByLicensePlate(licensePlate))
    }

    override fun getAll(): List<Car> {
        val listCar = mutableListOf<Car>()
        listCar.addAll(
            CarTranslator.fromCarsEntityToCarsDomain(database.carDao().getAllCars()!!)
        )
        return listCar
    }

}
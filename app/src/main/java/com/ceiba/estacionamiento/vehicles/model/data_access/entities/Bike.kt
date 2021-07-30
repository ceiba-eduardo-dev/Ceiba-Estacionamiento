package com.ceiba.estacionamiento.vehicles.model.data_access.entities

import com.ceiba.estacionamiento.shared.const.ConstParking
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.bike
import java.util.*

class Bike : VehicleEntity {
    constructor(date: String, plate: String, cylinder: Int, hour: String) {
        this.plate = plate
        this.cylinder = cylinder
        this.type = ConstParking.car
        this.date = date
        this.type = bike
        this.hour = hour
    }
}
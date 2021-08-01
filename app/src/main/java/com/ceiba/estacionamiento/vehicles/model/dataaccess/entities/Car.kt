package com.ceiba.estacionamiento.vehicles.model.dataaccess.entities

import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car

class Car : VehicleEntity {
    constructor(date: String, plate: String, hour: String) {
        this.plate = plate
        this.cylinder = 0
        this.type = car
        this.date = date
        this.hour = hour
    }
}
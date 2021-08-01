package com.ceiba.estacionamiento.vehicles.model.repositories

import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity

interface VehicleRepository {

    fun getAllVehicles(): List<VehicleEntity>
    fun insertVehicle(vehicleEntity: VehicleEntity): Long
    fun getVehicleByPlate(plate: String): VehicleEntity
    fun deleteVehicle(id: Int)
}
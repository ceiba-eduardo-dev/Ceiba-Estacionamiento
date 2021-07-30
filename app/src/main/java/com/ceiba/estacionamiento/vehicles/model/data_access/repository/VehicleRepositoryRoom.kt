package com.ceiba.estacionamiento.vehicles.model.data_access.repository

import com.ceiba.estacionamiento.vehicles.model.data_access.daos.VehicleDao
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity
import com.ceiba.estacionamiento.vehicles.model.repositories.VehicleRepository
import javax.inject.Inject

class VehicleRepositoryRoom @Inject constructor(
    private val vehicleDao: VehicleDao
) : VehicleRepository {

    override fun getAllVehicles(): List<VehicleEntity> {
        return vehicleDao.getAllVehicles()
    }

    override fun insertVehicle(entities: VehicleEntity): Long {
        return vehicleDao.insertVehicle(entities)
    }

    override fun getVehicleByPlate(plate: String): VehicleEntity {
        return vehicleDao.getVehicleByPlate(plate)
    }

    override fun deleteVehicle(id: Int) {
        return vehicleDao.deleteVehicle(id)
    }
}
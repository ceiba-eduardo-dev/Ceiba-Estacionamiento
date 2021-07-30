package com.ceiba.estacionamiento.vehicles.presenter.vehicle_entry

import androidx.lifecycle.LiveData
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity

interface OnVehicleEntryListener {
    fun saveVehicle(vehicleEntity: VehicleEntity)
    fun getResponseSaveVehicleDao(asLiveData: LiveData<Resource<Long>>)
    fun getVehicleByPlate(plate: String)
    fun getResponseQueryVehicleByPlateDao(asLiveData: LiveData<Resource<VehicleEntity>>)
}
package com.ceiba.estacionamiento.vehicles.presenter.vehicles

import androidx.lifecycle.LiveData
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity

interface OnVehiclesListener {
    fun onGetAllVehicles(allVehicle: LiveData<Resource<List<VehicleEntity>>>)
}
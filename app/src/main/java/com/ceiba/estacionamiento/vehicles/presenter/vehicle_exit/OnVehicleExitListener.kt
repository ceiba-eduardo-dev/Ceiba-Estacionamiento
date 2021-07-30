package com.ceiba.estacionamiento.vehicles.presenter.vehicle_exit

import androidx.lifecycle.LiveData
import com.ceiba.estacionamiento.shared.model.utils.Resource

interface OnVehicleExitListener {
    fun deleteVehicle(id: Int)
    fun getResponseDeleteVehicleDao(asLiveData: LiveData<Resource<Unit>>)
}
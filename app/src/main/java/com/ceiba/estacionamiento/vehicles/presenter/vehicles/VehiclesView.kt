package com.ceiba.estacionamiento.vehicles.presenter.vehicles

import androidx.lifecycle.LifecycleOwner
import com.ceiba.estacionamiento.shared.presenter.BaseView
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity

interface VehiclesView : BaseView {
    fun getLifecycleOwner(): LifecycleOwner
    fun setDataVehicles(data: List<VehicleEntity>?)
}
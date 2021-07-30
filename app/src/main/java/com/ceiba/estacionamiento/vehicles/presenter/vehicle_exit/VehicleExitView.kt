package com.ceiba.estacionamiento.vehicles.presenter.vehicle_exit

import androidx.lifecycle.LifecycleOwner
import com.ceiba.estacionamiento.shared.presenter.BaseView

interface VehicleExitView : BaseView {
    fun getLifecycleOwner(): LifecycleOwner
    fun navigateVehicles()
}
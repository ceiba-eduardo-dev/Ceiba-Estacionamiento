package com.ceiba.estacionamiento.vehicles.presenter.vehicle_entry

import androidx.lifecycle.LifecycleOwner
import com.ceiba.estacionamiento.shared.presenter.BaseView

interface VehicleEntryView : BaseView {
    fun getLifecycleOwner(): LifecycleOwner
    fun navigateListVehicles()
    fun onClickValidateVehicle()
    fun vehicleNoRegistry()
    fun showMessageContinue(message: String?)
}
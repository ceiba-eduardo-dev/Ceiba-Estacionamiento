package com.ceiba.estacionamiento.vehicles.presenter.vehicle_exit

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.shared.model.utils.Status
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class VehicleExitPresenter @Inject constructor(
    private val vehicleExitInteractor: VehicleExitInteractor

) : OnVehicleExitListener {

    var vehicleExitView: VehicleExitView? = null
        set(value) {
            field = value
            vehicleExitInteractor.onVehicleExitListener = this
        }

    override fun deleteVehicle(id: Int) {
        vehicleExitInteractor.deleteVehicle(id)
    }

    override fun getResponseDeleteVehicleDao(asLiveData: LiveData<Resource<Unit>>) {
        asLiveData.observe(vehicleExitView!!.getLifecycleOwner(), Observer { result ->
            when (result.status) {
                Status.LOADING -> vehicleExitView?.showProgress()
                Status.SUCCESS -> {
                    vehicleExitView?.hideProgress()
                    vehicleExitView?.showProgress()
                    vehicleExitView?.navigateVehicles()
                }
                Status.ERROR -> {
                    vehicleExitView?.hideProgress()
                    vehicleExitView?.showError(result.message)
                }
            }
        })
    }
}
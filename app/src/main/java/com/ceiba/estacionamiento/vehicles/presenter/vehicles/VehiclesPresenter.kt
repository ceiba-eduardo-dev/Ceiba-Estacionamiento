package com.ceiba.estacionamiento.vehicles.presenter.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.shared.model.utils.Status
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class VehiclesPresenter @Inject constructor(
    private val vehiclesInteractor: VehiclesInteractor
) : OnVehiclesListener {

    var vehiclesView: VehiclesView? = null
        set(value) {
            field = value
            vehiclesInteractor.onVehiclesListener = this
        }

    fun getAllVehicles() {
        vehiclesInteractor.getAllVehiclesFromRoom()
    }

    override fun onGetAllVehicles(allVehicle: LiveData<Resource<List<VehicleEntity>>>) {
        allVehicle.observe(vehiclesView!!.getLifecycleOwner(), Observer { result ->
            when (result.status) {
                Status.LOADING -> vehiclesView?.showProgress()
                Status.SUCCESS -> {
                    vehiclesView?.setDataVehicles(result.data)
                    vehiclesView?.hideProgress()
                }
                Status.ERROR -> {
                    vehiclesView?.setDataVehicles(null)
                    vehiclesView?.hideProgress()
                    vehiclesView?.showError(result.message)
                }
            }
        })
    }



}
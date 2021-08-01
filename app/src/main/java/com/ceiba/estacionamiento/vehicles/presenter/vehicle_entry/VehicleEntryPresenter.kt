package com.ceiba.estacionamiento.vehicles.presenter.vehicle_entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.shared.model.utils.Status
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class VehicleEntryPresenter
@Inject constructor(
    private val vehicleEntryInteractor: VehicleEntryInteractor
) : OnVehicleEntryListener {

    var vehicleEntryView: VehicleEntryView? = null
        set(value) {
            field = value
            vehicleEntryInteractor.onVehicleEntryListener = this
        }

    override fun saveVehicle(vehicleEntity: VehicleEntity) {
        vehicleEntryInteractor.addVehicle(vehicleEntity)
    }

    override fun getResponseSaveVehicleDao(asLiveData: LiveData<Resource<Long>>) {
        asLiveData.observe(vehicleEntryView!!.getLifecycleOwner(), Observer { result ->
            when (result.status) {
                Status.LOADING -> vehicleEntryView?.showProgress()
                Status.SUCCESS -> {
                    vehicleEntryView?.hideProgress()
                    vehicleEntryView?.navigateListVehicles()
                }
                Status.ERROR -> {
                    vehicleEntryView?.hideProgress()
                    vehicleEntryView?.showError(result.message)
                }
            }
        })
    }

    override fun getVehicleByPlate(plate: String) {
        vehicleEntryInteractor.getVehicleByPLateFromRoom(plate)
    }

    override fun getResponseQueryVehicleByPlateDao(asLiveData: LiveData<Resource<VehicleEntity>>) {
        asLiveData.observe(vehicleEntryView!!.getLifecycleOwner(), Observer { result ->
            when (result.status) {
                Status.LOADING -> vehicleEntryView?.showProgress()
                Status.SUCCESS -> {
                    vehicleEntryView?.hideProgress()
                    if (result.data == null) {
                        vehicleEntryView?.vehicleNoRegistry()
                    } else {
                        vehicleEntryView?.showError("Vehiculo encontrado")
                    }
                }
                Status.ERROR -> {
                    vehicleEntryView?.hideProgress()
                    vehicleEntryView?.showError(result.message)
                }
            }
        })
    }
}
/* @Inject constructor(
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

    override fun onGetAllVehicles(allVehicle: LiveData<Resource<List<Vehicle>>>) {
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

}*/
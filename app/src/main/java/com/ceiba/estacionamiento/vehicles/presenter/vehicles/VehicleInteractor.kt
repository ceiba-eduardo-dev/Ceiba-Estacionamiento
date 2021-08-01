package com.ceiba.estacionamiento.vehicles.presenter.vehicles

import androidx.lifecycle.asLiveData
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.vehicles.model.dataaccess.daos.VehicleDao
import com.ceiba.estacionamiento.vehicles.model.repositories.VehicleRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ActivityScoped
class VehiclesInteractor @Inject constructor(
    private val vehicleRepository: VehicleRepository,
    private val vehicleDao: VehicleDao
) {
    lateinit var onVehiclesListener: OnVehiclesListener

    fun getAllVehiclesFromRoom() {
        val flow = flow {
            val response = vehicleRepository.getAllVehicles().let {
                if (it.isNotEmpty()) {
                    emit(Resource.success(it))
                } else {
                    emit(Resource.error(null, 0, "Sin datos"))
                }
            }
        }
        onVehiclesListener.onGetAllVehicles(flow
            .onStart {
                emit(Resource.loading(null, null)) }
            .catch { ex ->
                emit(Resource.error(null, 0, ex.message))
            }.flowOn(Dispatchers.IO)
            .asLiveData()
        )

    }
}
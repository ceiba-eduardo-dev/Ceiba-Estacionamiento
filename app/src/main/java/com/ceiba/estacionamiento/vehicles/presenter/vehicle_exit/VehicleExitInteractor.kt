package com.ceiba.estacionamiento.vehicles.presenter.vehicle_exit

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.asLiveData
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.vehicles.model.repositories.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class VehicleExitInteractor @Inject constructor(
    val vehicleRepository: VehicleRepository
) {
    lateinit var onVehicleExitListener: OnVehicleExitListener

    fun deleteVehicle(id: Int) {
        val flow = flow {
            emit(
                Resource.success(
                    vehicleRepository.deleteVehicle(id),
                    message = "add_successfully"//context.getString(R.string.add_successfully)
                )
            )
        }
        onVehicleExitListener.getResponseDeleteVehicleDao(flow
            .onStart { emit(Resource.loading(null, null)) }
            .catch { exception ->
                with(exception) {
                    val msg = when (this) {
                        is SQLiteConstraintException -> "vehicle_is_already"//context.getString(R.string.movie_is_already)
                        else -> null
                    }
                    emit(com.ceiba.estacionamiento.shared.model.utils.Resource.error(null, 0, msg))
                }
            }
            .flowOn(Dispatchers.IO)
            .asLiveData())
    }
}

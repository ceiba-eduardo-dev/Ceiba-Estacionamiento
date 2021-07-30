package com.ceiba.estacionamiento.vehicles.presenter.vehicle_entry

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.asLiveData
import com.ceiba.estacionamiento.shared.model.utils.Resource
import com.ceiba.estacionamiento.vehicles.model.data_access.daos.VehicleDao
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity
import com.ceiba.estacionamiento.vehicles.model.repositories.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class VehicleEntryInteractor @Inject constructor(
    private val vehicleRepository: VehicleRepository,
) {
    lateinit var onVehicleEntryListener: OnVehicleEntryListener

    fun addVehicle(vehicle: VehicleEntity) {
        val flow = flow {
            emit(
                Resource.success(
                    vehicleRepository.insertVehicle(vehicle),
                    message = "add_successfully"//context.getString(R.string.add_successfully)
                )
            )
        }
        onVehicleEntryListener.getResponseSaveVehicleDao(flow
            .onStart { emit(Resource.loading(null, null)) }
            .catch { exception ->
                with(exception) {
                    val msg = when (this) {
                        is SQLiteConstraintException -> "vehicle_is_already"//context.getString(R.string.movie_is_already)
                        else -> null
                    }
                    emit(Resource.error(null, 0, msg))
                }
            }
            .flowOn(Dispatchers.IO)
            .asLiveData())
    }

    fun getVehicleByPLateFromRoom(plate: String) {
        val flow = flow {
            val response = vehicleRepository.getVehicleByPlate(plate).let {
                if (it == null) {
                    emit(Resource.success(it))
                } else {
                    emit(Resource.error(null, 0, "EL VEHICULO YA ESTA REGISTRADO"))
                }
            }
        }
        onVehicleEntryListener.getResponseQueryVehicleByPlateDao(flow
            .onStart {
                emit(Resource.loading(null, null))
            }
            .catch { ex ->
                emit(Resource.error(null, 0, ex.message))
            }.flowOn(Dispatchers.IO)
            .asLiveData()
        )

    }

}
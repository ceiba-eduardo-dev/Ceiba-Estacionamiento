package com.ceiba.estacionamiento.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ceiba.domain.service.ParkingCarService
import com.ceiba.domain.service.ParkingMotorcycleService
import com.ceiba.infrestructure.repository.CarRepositoryRoom
import com.ceiba.infrestructure.repository.MotorcycleRepositoryRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ParkingViewModel(
    context: Context
) : ViewModel() {
    private val carRepository = CarRepositoryRoom(context)
    private val motorcycleRepository = MotorcycleRepositoryRoom(context)
    //private val carService = ParkingCarService(carRepository)
    // private val motorcycleService = ParkingMotorcycleService(motorcycleRepository)


}
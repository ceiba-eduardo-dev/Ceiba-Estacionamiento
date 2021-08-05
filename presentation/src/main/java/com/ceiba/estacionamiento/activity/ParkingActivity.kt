package com.ceiba.estacionamiento.activity

import android.app.Dialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.Vehicle
import com.ceiba.domain.model.parking.CalendarParking
import com.ceiba.domain.repository.CarRepository
import com.ceiba.domain.service.ParkingCarService
import com.ceiba.domain.service.ParkingMotorcycleService
import com.ceiba.estacionamiento.BaseActivity
import com.ceiba.estacionamiento.adapter.VehiclesAdapter
import com.ceiba.estacionamiento.databinding.ActivityParkingBinding
import com.ceiba.estacionamiento.Status
import com.ceiba.estacionamiento.viewcomponent.ParkingVehicleEntryDialog
import com.ceiba.estacionamiento.viewmodel.ParkingViewModel
import com.ceiba.infrestructure.repository.CarRepositoryRoom
import com.ceiba.infrestructure.repository.MotorcycleRepositoryRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParkingActivity : BaseActivity() {
    companion object {
        const val motorcycle = "MOTO"
        const val car = "CARRO"
    }

    lateinit var carService: ParkingCarService
    lateinit var motorcycleService: ParkingMotorcycleService
    lateinit var viewModel: ParkingViewModel
    private lateinit var vehiclesAdapter: VehiclesAdapter
    private lateinit var vehiculos: MutableLiveData<List<Vehicle>>
    private var cars: List<Vehicle> = listOf()

    private lateinit var viewBinding: ActivityParkingBinding

    override fun initBinding(): ViewBinding {
        viewBinding = ActivityParkingBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initializeView() {
        val carRepository: CarRepository = CarRepositoryRoom(applicationContext)
        val motorcycleRepository = MotorcycleRepositoryRoom(applicationContext)
        carService = ParkingCarService(carRepository)
        vehiclesAdapter = VehiclesAdapter()

        viewBinding.vehicleList.adapter = vehiclesAdapter
        val linearManager = GridLayoutManager(this, 2)
        viewBinding.vehicleList.layoutManager = linearManager
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                carService.getAll()
            }
            vehiclesAdapter.setItemsList(result)
        }
        vehiclesAdapter.setItemsList(cars!!)
        viewBinding.floatingAddVehicle.setOnClickListener {
            ParkingVehicleEntryDialog().prepareDialog(this, arrayOf<String>(car, motorcycle))
        }
    }

    fun registryVehicle(
        licensePlate: String, type: String,
        cylinderCapacity: String, date: String,
        dialog: Dialog
    ) {
        try {
            val calendarParking = CalendarParking(date).getCalendar()
            val car = Car(licensePlate, calendarParking)
            carService.saveCar(car, "LUNES")
            showMessage("REGISTRADO CON EXITO")
            dialog.dismiss()
        } catch (ex: Exception) {
            showMessage(ex.message)
        }
    }


}

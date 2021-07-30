package com.ceiba.estacionamiento.vehicles.controller.views

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.viewbinding.ViewBinding
import com.ceiba.estacionamiento.R
import com.ceiba.estacionamiento.databinding.ActivityVehicleExitBinding
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.bike
import com.ceiba.estacionamiento.shared.controller.BaseActivity
import com.ceiba.estacionamiento.shared.controller.components.DatePickerComponent
import com.ceiba.estacionamiento.vehicles.model.valueobjects.DatePeriod
import com.ceiba.estacionamiento.vehicles.model.valueobjects.PricesParkingUC
import com.ceiba.estacionamiento.vehicles.presenter.vehicle_exit.VehicleExitPresenter
import com.ceiba.estacionamiento.vehicles.presenter.vehicle_exit.VehicleExitView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehicleExitActivity : BaseActivity(), VehicleExitView {
    private var date: String? = null
    private var hour: String? = null
    private lateinit var viewBinding: ActivityVehicleExitBinding

    @Inject
    lateinit var vehicleExitPresenter: VehicleExitPresenter

    override fun initBinding(): ViewBinding {
        viewBinding = ActivityVehicleExitBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initializeView() {
        requestData()
        vehicleExitPresenter.vehicleExitView = this
        viewBinding.vehicleTitle.text = vehicleRequest!!.plate
        viewBinding.btnExit.setOnClickListener {
            vehicleExitPresenter.deleteVehicle(vehicleRequest!!.idVehicle)
        }
        if (vehicleRequest?.type == bike) {
            viewBinding.vehicleImage.setImageResource(R.drawable.ic_bike)
            viewBinding.tvCylinder.text =
                "CILINDRAJE: " + vehicleRequest!!.cylinder.toString()
        }
        viewBinding.tvHour.text =
            "INGRESO : " + vehicleRequest!!.date + " " + vehicleRequest!!.hour + ":00:00"
        viewBinding.entryDates.setOnClickListener {
            showDateTimePiker()
        }

    }

    private fun showDateTimePiker() {
        viewBinding.btnExit.isEnabled = false
        date = null; hour = null
        viewBinding.entryDates.setText("")
        viewBinding.totalPrice.text = ""

        val datePicker =
            DatePickerComponent({ day, month, year ->
                onDateSelected(day, month, year)
            }, { hour -> onHourSelected(hour) })
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        date = "$year-"
        date += if (month < 10) {
            "0$month-"
        } else {
            "$month"
        }
        date += if (day < 10) {
            "0$day"
        } else {
            "$day"
        }
        viewBinding.entryDates.setText(date)
    }

    private fun onHourSelected(hour: Int) {
        this.hour = hour.toString()
        val hourString = viewBinding.entryDates.text.toString() + " $hour:00:00"
        viewBinding.entryDates.setText(hourString)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calculatePrice()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculatePrice() {
        val datePeriod = DatePeriod(vehicleRequest!!.date, date!!).totalHoursByDate() + 1
        val hoursTotal = datePeriod + (hour?.toInt()?.minus(vehicleRequest?.hour?.toInt()!!)!!)
        if (hoursTotal < 0) {
            showError("LA hora de salida no puede ser menor a la hora de ingreso")
        } else {
            val pricesParkingUC = PricesParkingUC()
            var price = pricesParkingUC.calculatePrice(hoursTotal.toInt(), vehicleRequest!!)
            if (vehicleRequest?.type == bike) {
                price = pricesParkingUC.aggregatePriceForCc(price, vehicleRequest)
            }
            viewBinding.totalPrice.text = price.toString()
            viewBinding.btnExit.isEnabled = true
        }
    }

    override fun getLifecycleOwner() = this

    override fun navigateVehicles() {
        val intent = Intent(applicationContext, VehiclesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(intent)
    }
}

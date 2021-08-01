package com.ceiba.estacionamiento.vehicles.controller.views

import android.R
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.viewbinding.ViewBinding
import com.ceiba.estacionamiento.databinding.ActivityVehicleEntryBinding
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.bike
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car
import com.ceiba.estacionamiento.shared.controller.BaseActivity
import com.ceiba.estacionamiento.shared.controller.components.DatePickerComponent
import com.ceiba.estacionamiento.shared.controller.extensions.showMessageContinue
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.Bike
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.Car
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity
import com.ceiba.estacionamiento.vehicles.presenter.vehicle_entry.VehicleEntryPresenter
import com.ceiba.estacionamiento.vehicles.presenter.vehicle_entry.VehicleEntryView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehicleEntryActivity : BaseActivity(), VehicleEntryView, AdapterView.OnItemSelectedListener {
    private var type: String? = null
    private var date: String? = null
    private var hour: String? = null
    private var plate: String? = null
    private lateinit var viewBinding: ActivityVehicleEntryBinding

    @Inject
    lateinit var vehicleEntryPresenter: VehicleEntryPresenter

    override fun initBinding(): ViewBinding {
        viewBinding = ActivityVehicleEntryBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initializeView() {
        vehicleEntryPresenter.vehicleEntryView = this
        val arrayAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, arrayOf(car, bike))
        viewBinding.spTypeVehicle.adapter = arrayAdapter
        viewBinding.spTypeVehicle.onItemSelectedListener = this
        viewBinding.btnRegistryVehicle.setOnClickListener {
            onClickValidateVehicle()
        }
        viewBinding.entryDates.setOnClickListener {
            showDateTimePiker()
        }

    }

    private fun showDateTimePiker() {
        date = null; hour = null
        viewBinding.entryDates.setText("")
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
    }

    override fun getLifecycleOwner() = this
    override fun navigateListVehicles() {
        val intent = Intent(applicationContext, VehiclesActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        applicationContext.startActivity(intent)
    }

    override fun onClickValidateVehicle() {
        plate = viewBinding.etPlate.text.trim().toString().toUpperCase();
        if (plate!!.isNotBlank()) {
            vehicleEntryPresenter.getVehicleByPlate(plate!!)
        } else {
            showError("Ingrese la placa")
        }
    }

    override fun vehicleNoRegistry() {
        val cc = viewBinding.etCylinder.text.toString()
        if (date == null || hour == null) {
            showError("INGRESE LA FECHA Y HORA")
        } else if (type == bike && cc.isBlank()) {
            showError("INGRESE EL CILINDRAJE")
        } else {
            var vehicle = VehicleEntity()
            vehicle = if (type == bike) {
                Bike(date!!, plate!!, cc.toInt(), hour!!)
            } else {
                Car(date!!, plate!!, hour!!)
            }
            vehicleEntryPresenter.saveVehicle(vehicle)
        }

    }

    override fun showMessageContinue(message: String?) {
        message?.showMessageContinue(this) { navigateListVehicles() }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        type = parent?.getItemAtPosition(position).toString()
        viewBinding.etCylinder.isEnabled = type == bike
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}
package com.ceiba.estacionamiento.viewcomponent

import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.*
import com.ceiba.estacionamiento.R
import com.ceiba.estacionamiento.activity.ParkingActivity

class ParkingVehicleEntryDialog : AdapterView.OnItemSelectedListener {
    companion object {
        const val motorcycle = "MOTO"
    }

    private lateinit var editTextDate: EditText
    private lateinit var licensePlate: EditText
    private var type: String = "CARRO"
    lateinit var typesVehicles: Array<String>
    lateinit var spType: Spinner
    lateinit var cylinderCapacity: EditText
    private lateinit var dialog: Dialog
    lateinit var picker: DatePickerComponent
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    fun prepareDialog(activity: ParkingActivity, typesVehicles: Array<String>) {
        this.typesVehicles = typesVehicles
        dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_entry)
        initView(activity, typesVehicles)
        dialog.show()
    }

    private fun initView(activity: ParkingActivity, typesVehicles: Array<String>) {
        //Find
        editTextDate = dialog.findViewById(R.id.entry_dates)
        licensePlate = dialog.findViewById(R.id.et_licens_plate)
        spType = dialog.findViewById(R.id.spTypeVehicle)
        cylinderCapacity = dialog.findViewById(R.id.et_cylinder_capacity)
        btnSave = dialog.findViewById(R.id.btnRegistryVehicle)
        btnCancel = dialog.findViewById(R.id.btnCancelEntry)

        //SetSpinner
        val arrayAdapter =
            ArrayAdapter(activity, R.layout.support_simple_spinner_dropdown_item, typesVehicles)
        spType.adapter = arrayAdapter
        spType.onItemSelectedListener = this

        //OnClick
        editTextDate.setOnClickListener {
            picker = DatePickerComponent { date -> setTime(date) }
            picker.show(activity.supportFragmentManager, "datePicker")
        }
        btnSave.setOnClickListener {
            activity.registryVehicle(
                licensePlate.text.toString(),
                type,
                cylinderCapacity.text.toString(),
                editTextDate.text.toString(),
                this.dialog
            )
        }

        btnCancel.setOnClickListener {
            dialog.cancel()
        }
    }

    private fun setTime(date: String) {
        editTextDate.setText(date)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        type = parent?.getItemAtPosition(position).toString()
        cylinderCapacity.isEnabled = type == motorcycle
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

}
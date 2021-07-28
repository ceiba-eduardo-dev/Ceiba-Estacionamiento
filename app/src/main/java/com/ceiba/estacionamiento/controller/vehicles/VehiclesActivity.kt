package com.ceiba.estacionamiento.controller.vehicles

import androidx.viewbinding.ViewBinding
import com.ceiba.estacionamiento.controller.BaseActivity
import com.ceiba.estacionamiento.databinding.ActivityVehiclesBinding

class VehiclesActivity : BaseActivity() {

    lateinit var viewBinding: ActivityVehiclesBinding

    override fun initBinding(): ViewBinding {
        viewBinding = ActivityVehiclesBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initializeView() {
        viewBinding.hola.text = "Hola"
    }

}
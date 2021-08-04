package com.ceiba.estacionamiento

import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.ceiba.estacionamiento.databinding.ActivityVehiclesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehiclesActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityVehiclesBinding

    override fun initBinding(): ViewBinding {
        viewBinding = ActivityVehiclesBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initializeView() {
    }

}
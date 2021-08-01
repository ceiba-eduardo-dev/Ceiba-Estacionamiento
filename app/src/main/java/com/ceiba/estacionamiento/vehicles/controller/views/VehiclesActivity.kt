package com.ceiba.estacionamiento.vehicles.controller.views

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewbinding.ViewBinding
import com.ceiba.estacionamiento.shared.controller.BaseActivity
import com.ceiba.estacionamiento.databinding.ActivityVehiclesBinding
import com.ceiba.estacionamiento.vehicles.controller.adapters.VehiclesAdapter
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity
import com.ceiba.estacionamiento.vehicles.presenter.vehicles.VehiclesPresenter
import com.ceiba.estacionamiento.vehicles.presenter.vehicles.VehiclesView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehiclesActivity : BaseActivity(), VehiclesView {

    private lateinit var viewBinding: ActivityVehiclesBinding
    private lateinit var vehiclesAdapter: VehiclesAdapter

    @Inject
    lateinit var vehiclesPresenter: VehiclesPresenter

    override fun initBinding(): ViewBinding {
        viewBinding = ActivityVehiclesBinding.inflate(layoutInflater)
        return viewBinding
    }

    override fun initializeView() {
        vehiclesPresenter.vehiclesView = this
        val linearManager = GridLayoutManager(this, 2)
        vehiclesAdapter = VehiclesAdapter()
        viewBinding.vehicleList.adapter = vehiclesAdapter
        viewBinding.vehicleList.layoutManager = linearManager
        vehiclesPresenter.getAllVehicles()
        viewBinding.floatingAddVehicle.setOnClickListener {
            val intent = Intent(applicationContext, VehicleEntryActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            applicationContext.startActivity(intent)
        }
    }

    override fun getLifecycleOwner() = this

    override fun setDataVehicles(data: List<VehicleEntity>?) {
        data?.let { vehiclesAdapter.setItemsList(it) }
    }


}
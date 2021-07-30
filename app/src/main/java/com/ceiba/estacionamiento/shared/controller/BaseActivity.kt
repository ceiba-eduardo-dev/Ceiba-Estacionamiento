package com.ceiba.estacionamiento.shared.controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.vehicleSerializable
import com.ceiba.estacionamiento.shared.controller.extensions.showMessage
import com.ceiba.estacionamiento.shared.presenter.BaseView
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), BaseView {
    var vehicleRequest: VehicleEntity? = null

    private lateinit var binding: ViewBinding
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        setContentView(binding.root)
        initializeView()
    }

    /**
     * Use este metodo para inicializar los componentes de la vista
     */
    protected abstract fun initBinding(): ViewBinding


    /**
     * Use este metodo para iniciar toda la logica de la vista
     */
    abstract fun initializeView()

    override fun showProgress() {
        //"cargando".showMessage(this)
    }

    fun requestData() {
        vehicleRequest = intent.getSerializableExtra(vehicleSerializable) as VehicleEntity
    }

    override fun hideProgress() {
        //"cargado".showMessage(this)
    }

    override fun showError(message: String?) {
        message?.showMessage(this)
    }
}
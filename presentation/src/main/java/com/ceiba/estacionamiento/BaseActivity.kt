package com.ceiba.estacionamiento

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity(), BaseView {

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

    override fun hideProgress() {
        //"cargado".showMessage(this)
    }

    override fun showError(message: String?) {
        MaterialAlertDialogBuilder(this)
            .setMessage(message)
            .setPositiveButton("Ok", null)
            .show()
    }
}
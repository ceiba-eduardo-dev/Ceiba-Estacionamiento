package com.ceiba.estacionamiento.controller

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

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

    abstract fun initializeView()


}
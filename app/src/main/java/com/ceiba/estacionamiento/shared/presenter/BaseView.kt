package com.ceiba.estacionamiento.shared.presenter


interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(message: String?)
}
package com.ceiba.estacionamiento


interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String?)
}
package com.ceiba.domain.exception

import java.lang.RuntimeException

class VehicleDeleteException : RuntimeException() {
    companion object {
        const val messageError = "ERROR AL ELIMINAR EL VEHICULO"
    }

    override val message: String?
        get() = messageError
}
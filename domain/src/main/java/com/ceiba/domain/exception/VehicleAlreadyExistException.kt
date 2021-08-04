package com.ceiba.domain.exception

import java.lang.RuntimeException

class VehicleAlreadyExistException : RuntimeException() {
    companion object {
        const val messageError = "ERROR EL VEHICULO YA SE ENCUENTRA REGISTRADO"
    }

    override val message: String?
        get() = messageError
}
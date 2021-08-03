package com.ceiba.domain.exception

import java.lang.RuntimeException

class ParkingCarServiceException : RuntimeException() {
    companion object {
        const val messageError = "EL VEHICULO NO PUEDE ENTRAR EL DÍA LUNES"
    }

    override val message: String?
        get() = messageError
}
package com.ceiba.domain.exception

import java.lang.RuntimeException

class CalendarParkingException : RuntimeException() {
    companion object {
        const val messageError = "ERROR AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"
    }

    override val message: String?
        get() = messageError
}
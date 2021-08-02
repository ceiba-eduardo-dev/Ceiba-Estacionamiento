package com.ceiba.domain.exception

class CalendarParkingException(cause: Throwable?) : Exception(cause) {
    companion object {
        const val messageError = "ERROR AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"
    }

    override val message: String?
        get() = messageError
}
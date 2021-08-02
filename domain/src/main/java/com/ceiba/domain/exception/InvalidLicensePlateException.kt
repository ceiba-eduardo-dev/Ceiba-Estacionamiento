package com.ceiba.domain.exception

import java.lang.RuntimeException

class InvalidLicensePlateException : RuntimeException() {
    companion object {
        const val messageError = "ERROR EN EL FORMATO DE LA PLACA"
    }

    override val message: String?
        get() = messageError
}
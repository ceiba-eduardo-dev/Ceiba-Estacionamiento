package com.ceiba.domain.model


abstract class Vehicle(
    private val licensePlate: String
) {
    private val regularExpressionLicensePlate =
        "[a-zA-Z]{3}[0-9]{3}|[a-zA-Z]{3}[0-9]{2}[a-zA-Z]$"

    fun validateLicensePlateFormat(): Boolean {
        val regex = regularExpressionLicensePlate.toRegex()
        return regex.containsMatchIn(licensePlate) && licensePlate.length == 6
    }

    val getLicensePlate: String
        get() = licensePlate
}
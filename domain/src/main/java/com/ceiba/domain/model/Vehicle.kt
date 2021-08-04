package com.ceiba.domain.model

import com.ceiba.domain.exception.InvalidLicensePlateException
import java.util.*


abstract class Vehicle {
    private var licensePlate: String
    private var dateEntry: Calendar

    constructor(licensePlate: String, dateEntry: Calendar) {
        if (!validateLicensePlateFormat(licensePlate))
            throw InvalidLicensePlateException()
        this.licensePlate = licensePlate
        this.dateEntry = dateEntry
    }

    private val regularExpressionLicensePlate =
        "[a-zA-Z]{3}[0-9]{3}|[a-zA-Z]{3}[0-9]{2}[a-zA-Z]$"

    private fun validateLicensePlateFormat(licensePlate: String): Boolean {
        val regex = regularExpressionLicensePlate.toRegex()
        return regex.containsMatchIn(licensePlate) && licensePlate.length == 6
    }

    val getLicensePlate: String
        get() = licensePlate

    val getDateEntry: Calendar
        get() = dateEntry
}
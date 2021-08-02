package com.ceiba.domain.model

import com.ceiba.domain.exception.CalendarParkingException

abstract class Vehicle {
    private lateinit var licensePlate: String

    constructor(licensePlate: String) {
        validateLicensePlateFormat()
    }

    fun validateLicensePlateFormat(): Boolean {
        return true
    }

    val getLicensePlate: String
        get() = licensePlate
}
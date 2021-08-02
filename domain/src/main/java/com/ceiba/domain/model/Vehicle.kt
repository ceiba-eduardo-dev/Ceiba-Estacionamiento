package com.ceiba.domain.model

abstract class Vehicle {
    private lateinit var licensePlate: String

    constructor(licensePlate: String) {
        validateLicensePlateFormat()
    }

    fun validateLicensePlateFormat(): Boolean {
        return true
    }
}
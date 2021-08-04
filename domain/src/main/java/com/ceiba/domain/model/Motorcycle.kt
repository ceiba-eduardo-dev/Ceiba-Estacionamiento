package com.ceiba.domain.model

import java.util.*

class Motorcycle(
    licensePlate: String,
    private val cylinderCapacity: Int,
    dateEntry: Calendar
) : Vehicle(licensePlate, dateEntry) {
    fun getCylinderCapacity(): Int {
        return cylinderCapacity
    }
}
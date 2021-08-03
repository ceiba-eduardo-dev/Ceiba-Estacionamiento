package com.ceiba.domain.model

class Motorcycle(
    licensePlate: String,
    private val cylinderCapacity: Int
) : Vehicle(licensePlate) {
    fun getCylinderCapacity(): Int {
        return cylinderCapacity
    }
}
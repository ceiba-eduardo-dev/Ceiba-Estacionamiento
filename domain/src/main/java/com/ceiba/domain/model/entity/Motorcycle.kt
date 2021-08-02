package com.ceiba.domain.model.entity

class Motorcycle(
    private val licensePlate: String,
    private val cylinderCapacity: Int
) : Vehicle(licensePlate) {
    fun getCylinderCapacity(): Int = cylinderCapacity
}
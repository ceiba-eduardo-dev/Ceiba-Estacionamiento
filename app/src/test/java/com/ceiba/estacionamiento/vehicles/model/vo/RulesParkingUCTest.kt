package com.ceiba.estacionamiento.vehicles.model.vo

import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.monday
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.Car
import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.VehicleEntity
import com.ceiba.estacionamiento.vehicles.model.valueobjects.RulesParkingUC
import junit.framework.TestCase
import org.junit.Test

class RulesParkingUCTest : TestCase() {
    private val rulesParkingUC = RulesParkingUC()

    @Test
    fun testValidateAccessForDay() {
        val result = rulesParkingUC.validateAccessForDay(monday, car, Car("2021-11-11", "ATP14F", "12"))
        assertEquals(true, result)
    }

    @Test
    fun testValidateAccessQuantityByType() {
        val list: MutableList<VehicleEntity> =
            mutableListOf(Car("2021-11-11", "PTP14F", "12"), Car("2021-11-11", "PTP14F", "12"))
        val result = rulesParkingUC.validateAccessQuantityByType(list, car)
        assertEquals(true, result)
    }

}
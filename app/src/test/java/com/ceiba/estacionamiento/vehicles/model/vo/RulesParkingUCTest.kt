package com.ceiba.estacionamiento.domain.uc

import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.car
import com.ceiba.estacionamiento.shared.const.ConstParking.Companion.monday
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.Car
import com.ceiba.estacionamiento.vehicles.model.data_access.entities.VehicleEntity
import com.ceiba.estacionamiento.vehicles.model.vo.RulesParkingUC
import junit.framework.TestCase
import org.junit.Test

class RulesParkingUCTest : TestCase() {
    private val rp = RulesParkingUC()

    @Test
    fun testValidateAccessForDay() {
        val result = rp.validateAccessForDay(monday, car, Car("2021-11-11", "PTP14F", "12"))
        assertEquals(false, result)
    }

    @Test
    fun testValidateAccessQuantityByType() {
        val list: MutableList<VehicleEntity> =
            mutableListOf(Car("2021-11-11", "PTP14F", "12"), Car("2021-11-11", "PTP14F", "12"))
        val result = rp.validateAccessQuantityByType(list, car)
        assertEquals(true, result)
    }

}
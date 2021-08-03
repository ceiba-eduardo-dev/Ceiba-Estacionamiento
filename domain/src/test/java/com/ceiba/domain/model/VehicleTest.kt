package com.ceiba.domain.model

import junit.framework.TestCase

import org.junit.Test

class VehicleTest : TestCase() {

    @Test
    fun testValidateLicensePlateFormat_withLicensePlate_correct() {
        val validatePlate = Car("PTP14F").validateLicensePlateFormat()
        assertEquals(true, validatePlate)
    }

    @Test
    fun testGetLicensePlate_notEmpty_success() {
        val validatePlate = Car("PTP14F")
        assertEquals(true, validatePlate.validateLicensePlateFormat())
        assertEquals(true, (validatePlate.getLicensePlate.isNotEmpty()))
    }
}
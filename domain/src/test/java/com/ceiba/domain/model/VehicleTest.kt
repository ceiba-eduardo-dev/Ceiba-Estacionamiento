package com.ceiba.domain.model

import com.ceiba.domain.model.parking.CalendarParking
import junit.framework.TestCase

import org.junit.Test
import java.lang.Exception

class VehicleTest : TestCase() {
    companion object {
        const val formatDate = "11-05-2021 05:05:00"
        const val messageExpected = "ERROR EN EL FORMATO DE LA PLACA"
    }

    @Test
    fun testValidateLicensePlateFormat_withLicensePlate_correct() {
        //Arrange
        var calendarParking = CalendarParking(formatDate).getCalendar()

        //Act
        val car = Car("PTP14F", calendarParking);

        //Assert
        assertNotNull(car.getLicensePlate)
    }

    @Test
    fun testGetLicensePlate_notEmpty_success() {
        //Arrange
        var calendarParking = CalendarParking(formatDate).getCalendar()

        try {
            //Act
            val car = Car("123456", calendarParking);
        } catch (exception: Exception) {
            //Assert
            assertEquals(messageExpected, exception.message)
        }

    }

    @Test
    fun testGetLicensePlate_stringEmpty_success() {
        //Arrange
        var calendarParking = CalendarParking(formatDate).getCalendar()

        try {
            //Act
            val car = Car("", calendarParking);
        } catch (exception: Exception) {
            //Assert
            assertEquals(messageExpected, exception.message)
        }
    }
}
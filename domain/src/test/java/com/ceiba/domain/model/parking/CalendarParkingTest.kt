package com.ceiba.domain.model.parking

import junit.framework.TestCase
import org.junit.Test
import java.util.*

class CalendarParkingTest : TestCase() {
    companion object {
        const val stringDate = "01-08-2021 23:02:00"
        const val messageExpected = "ERROR AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"
    }

    @Test
    fun testGetCalendar_fromString_assertDay_success() {
        //Arrange - Act
        val calendar = CalendarParking(stringDate).getCalendar()

        //Assert
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun testGetCalendar_fromString_assertDay_error() {
        //Arrange - Act
        val calendar = CalendarParking(stringDate).getCalendar()

        //Assert
        assertEquals(true, (2 != calendar.get(Calendar.DAY_OF_MONTH)))
    }

    @Test
    fun testGetCalendar_fromString_assertNotNull_success() {
        //Arrange - Act
        val calendar = CalendarParking(stringDate).getCalendar()

        //Assert
        assertEquals(true, (calendar.get(Calendar.DAY_OF_MONTH) != null))
    }

    @Test
    fun testGetCalendar_fromString_assertException_success() {
        //Arrange - Act
        try {
            CalendarParking("error de cadena").getCalendar()
        } catch (e: Exception) {
            //Assert
            assertEquals(messageExpected, e.message)
        }

    }
}
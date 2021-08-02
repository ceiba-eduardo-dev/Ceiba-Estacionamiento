package com.ceiba.domain.model.valueobject

import com.ceiba.domain.exception.CalendarParkingException
import junit.framework.TestCase
import org.junit.Test
import java.util.*

class CalendarParkingTest : TestCase() {
    private val stringDate = "01-08-2021 23:02:00"

    @Test
    fun testGetCalendar_fromString_assertDay_success() {
        val calendar = CalendarParking(stringDate).getCalendar()
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH))
    }

    @Test
    fun testGetCalendar_fromString_assertDay_error() {
        val calendar = CalendarParking(stringDate).getCalendar()
        assertEquals(true, (2 != calendar.get(Calendar.DAY_OF_MONTH)))
    }

    @Test
    fun testGetCalendar_fromString_assertNotNull_success() {
        val calendar = CalendarParking(stringDate).getCalendar()
        assertEquals(true, (null != calendar.get(Calendar.DAY_OF_MONTH)))
    }

    @Test
    fun testGetCalendar_fromString_assertException_success() {
        try {
            CalendarParking("error de cadena").getCalendar()
        } catch (e: Exception) {
            assertEquals(CalendarParkingException.messageError, e.message)
        }

    }
}
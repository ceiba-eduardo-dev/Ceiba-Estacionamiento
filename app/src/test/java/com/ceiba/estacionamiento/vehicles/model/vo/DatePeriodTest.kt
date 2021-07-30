package com.ceiba.estacionamiento.vehicles.model.vo

import junit.framework.TestCase

class DatePeriodTest : TestCase() {
    fun testTotalHoursByDate() {
        val datePeriod = DatePeriod("2021-11-11", "2021-11-12")
        assertEquals(24, datePeriod.totalHoursByDate())
    }
}
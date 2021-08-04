package com.ceiba.domain.model

import com.ceiba.domain.model.parking.CalendarParking
import junit.framework.TestCase

import org.junit.Test


class MotorcycleTest : TestCase() {

    @Test
    fun testGetCylinderCapacity_capacity500_correct() {
        //Arrange
        val calendar = CalendarParking("11-05-2021 05:05:00").getCalendar()
        val motorcycle = Motorcycle("PTP14F", 500, calendar)

        //Act
        val cylinderCapacity = motorcycle.getCylinderCapacity()

        //Assert
        assertEquals(500, cylinderCapacity)
    }

}
package com.ceiba.domain.model.entity

import com.ceiba.domain.model.Motorcycle
import junit.framework.TestCase

import org.junit.Test


class MotorcycleTest : TestCase() {

    @Test
    fun testGetCylinderCapacity_capacity500_correct() {
        val cylinderCapacity = Motorcycle("PTP14F", 500).getCylinderCapacity()
        assertEquals(500, cylinderCapacity)
    }
}
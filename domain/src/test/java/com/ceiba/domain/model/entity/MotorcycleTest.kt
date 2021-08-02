package com.ceiba.domain.model.entity

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class MotorcycleTest : TestCase() {

    @Test
    fun testGetCylinderCapacity_notNull_correct() {
        val cylinderCapacity = Motorcycle("PTP14F", 180)
        assertEquals(true, (cylinderCapacity != null))
    }

    @Test
    fun testGetCylinderCapacity_capacity500_correct() {
        val cylinderCapacity = Motorcycle("PTP14F", 500).getCylinderCapacity()
        assertEquals(500, cylinderCapacity)
    }
}
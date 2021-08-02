package com.ceiba.domain.model.entity

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class MotorcycleTest : TestCase() {

    @Test
    fun testGetCylinderCapacity_NotNull() {
        val cylinderCapacity = Motorcycle("PTP14F", 180)
        assertEquals(true ,(cylinderCapacity != null))
    }
}
package com.ceiba.domain.model.service

import com.ceiba.domain.model.entity.Motorcycle
import com.ceiba.domain.model.repository.RepositoryMotorcycle
import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class ParkingMotorcycleServiceTest : TestCase() {

    @Test
    fun testGetPriceByCylinderCapacity_maxCylinderCapacityOvercome_correct() {
        val motorcycle = Motorcycle("PTP14F", 600);
        assertEquals(
            2000,
            ParkingMotorcycleService().getPriceByCylinderCapacity(motorcycle)
        )
    }

    @Test
    fun testGetPriceByCylinderCapacity_cylinderCapacityNotReached_correct() {
        val motorcycle = Motorcycle("PTP14F", 200);
        val expected = ParkingMotorcycleService().getPriceByCylinderCapacity(motorcycle)
        assertEquals(
            0,
            expected
        )
    }
}
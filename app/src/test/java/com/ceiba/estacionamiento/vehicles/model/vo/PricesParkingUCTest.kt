package com.ceiba.estacionamiento.vehicles.model.vo

import com.ceiba.estacionamiento.vehicles.model.dataaccess.entities.Bike
import com.ceiba.estacionamiento.vehicles.model.valueobjects.PricesParkingUC
import junit.framework.TestCase
import org.junit.Test

class PricesParkingUCTest : TestCase() {
    private val pp = PricesParkingUC()

    @Test
    fun testCalculatePrice() {
        val vehicle = Bike("", "PTP14F", 600, "11")
        val result = pp.calculatePrice(1, vehicle)
        assertEquals(500, result)
    }

    @Test
    fun testAggregatePriceForCc() {
        val vehicle = Bike("2021-11-11", "PTP14F", 500, "12")
        val price = pp.calculatePrice(1, vehicle)
        val result = pp.aggregatePriceForCc(price, vehicle)
        assertEquals(2500, result)
    }

}
package com.ceiba.domain.service

import com.ceiba.domain.model.Car
import com.ceiba.domain.repository.CarRepository
import junit.framework.TestCase
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.lang.Exception


class ParkingCarServiceTest : TestCase() {
    private val monday = "LUNES"
    private val messageException = "EL VEHICULO NO PUEDE ENTRAR EL DÍA LUNES"

    @Mock
    lateinit var carRepository: CarRepository

    @Before
    override fun setUp() {
        carRepository = Mockito.mock(CarRepository::class.java)
    }

    @Test
    fun testValidateAccessCarForDay_dayMonday_success() {
        val parkingService = ParkingCarService(carRepository)
        val access = parkingService.validateAccessCarForDay(Car("KJH245"), monday)
        assertEquals(true, access)
    }

    @Test
    fun testValidateAccessCarForDay_dayMonday_exception() {
        val parkingService = ParkingCarService(carRepository)
        try {
            parkingService.validateAccessCarForDay(Car("AJH245"), monday)
        } catch (exception: Exception) {
            assertEquals(messageException, exception.message)
        }
    }

    @Test
    fun testValidateAccessCarForDay_licensePlateInBlank_exception() {
        val parkingService = ParkingCarService(carRepository)
        val access = parkingService.validateAccessCarForDay(Car(""), monday)
        assertEquals(true, access)
    }

}
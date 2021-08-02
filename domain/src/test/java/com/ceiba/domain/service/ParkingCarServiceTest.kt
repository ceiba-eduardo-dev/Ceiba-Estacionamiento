package com.ceiba.domain.service

import com.ceiba.domain.repository.CarRepository
import junit.framework.TestCase
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class ParkingCarServiceTest : TestCase() {

    @Mock
    lateinit var carRepository: CarRepository

    @Before
    override fun setUp() {
        carRepository = Mockito.mock(CarRepository::class.java)
    }

    @Test
    fun testValidateAccessCarForDay_dayMonday_success() {
        Mockito.`when`(carRepository.validateAccessCarForDay("EJH245", "lunes"))
            .thenReturn(true)
        val access = carRepository.validateAccessCarForDay("EJH245", "lunes")
        assertEquals(true, access)
    }

    @Test
    fun testValidateAccessCarForDay_dayMonday_error() {
        Mockito.`when`(carRepository.validateAccessCarForDay("AJH245", "lunes"))
            .thenReturn(false)
        val access = carRepository.validateAccessCarForDay("AJH245", "lunes")
        assertEquals(false, access)
    }
}
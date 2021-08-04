package com.ceiba.domain.service

import com.ceiba.domain.model.Car
import com.ceiba.domain.model.parking.CalendarParking
import com.ceiba.domain.repository.CarRepository
import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito

class VehicleServiceTest : TestCase() {
    @Mock
    lateinit var carRepository: CarRepository

    @Test
    fun testCalculatePriceForHours_withCar_price100() {
        //Arrange
        carRepository = Mockito.mock(CarRepository::class.java)
        val calendarParking = CalendarParking("11-05-2021 05:05:00").getCalendar()
        val car = Car("AJH245", calendarParking)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val resultPriceHourCar = parkingService.calculatePriceByCar(1)

        //Assert
        assertEquals(1000, resultPriceHourCar)
    }

    @Test
    fun testCalculatePriceForHours_withCar_price200() {
        //Arrange
        carRepository = Mockito.mock(CarRepository::class.java)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val resultPriceHourCar = parkingService.calculatePriceForHours(2, 8000, 1000)

        //Assert
        assertEquals(2000, resultPriceHourCar)
    }
}
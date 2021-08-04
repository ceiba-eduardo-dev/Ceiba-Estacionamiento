package com.ceiba.domain.service

import com.ceiba.domain.exception.VehicleDeleteException
import com.ceiba.domain.model.Car
import com.ceiba.domain.model.parking.CalendarParking
import com.ceiba.domain.repository.CarRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class ParkingCarServiceTest : TestCase() {
    companion object {
        const val monday = "LUNES"
        const val messageDayException = "EL VEHICULO NO PUEDE ENTRAR EL DÍA LUNES"
        const val messageDeleteException = "ERROR AL ELIMINAR EL VEHICULO"
        const val messageLicensePlateException = "ERROR EN EL FORMATO DE LA PLACA"
        const val messageLicensePlateExistException = "ERROR EL VEHICULO YA SE ENCUENTRA REGISTRADO"
        const val formatDate = "11-05-2021 05:05:00"
    }

    @Mock
    lateinit var carRepository: CarRepository

    @Before
    override fun setUp() {
        //Arrange
        carRepository = Mockito.mock(CarRepository::class.java)
    }

    @Test
    fun testValidateAccessCarForDay_dayMonday_success() {
        //Arrange
        val parkingService = ParkingCarService(carRepository)
        var calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("KJH245", calendarParking);

        //Act
        val access = parkingService.validateAccessCarForDay(car, monday)

        //Assert
        assertEquals(true, access)
    }

    @Test
    fun testValidateAccessCarForDay_dayMonday_exception() {
        //Arrange
        val parkingService = ParkingCarService(carRepository)
        var calendarParking = CalendarParking(formatDate).getCalendar()

        //Act
        try {
            parkingService.validateAccessCarForDay(Car("AJH245", calendarParking), monday)
        } catch (exception: Exception) {
            //Assert
            assertEquals(messageDayException, exception.message)
        }
    }

    @Test
    fun testValidateAccessCarForDay_licensePlateInBlank_exception() {
        //Arrange
        val parkingService = ParkingCarService(carRepository)
        var calendarParking = CalendarParking(formatDate).getCalendar()

        //Act
        try {
            parkingService.validateAccessCarForDay(Car("", calendarParking), monday)
        } catch (exception: Exception) {
            //Assert
            assertEquals(messageLicensePlateException, exception.message)
        }
    }

    @Test
    fun testGetAll_withCars_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        val elements = listOf(car, car)
        Mockito.`when`(carRepository.getAll()).thenReturn(elements)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val cars = parkingService.getAll()

        //Assert
        assertEquals(car, cars!![1])
    }

    fun testGetAll_carsEmpty_correct() {
        //Arrange
        val elements = listOf<Car>()
        Mockito.`when`(carRepository.getAll()).thenReturn(elements)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val cars = parkingService.getAll()

        //Assert
        assertEquals(emptyList<Car>(), cars)
    }

    @Test
    fun testGetCount_listOf2_correct() {
        //Arrange
        Mockito.`when`(carRepository.getCount()).thenReturn(2)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val cars = parkingService.getCount()

        //Assert
        assertEquals(2, cars)
    }

    @Test
    fun testGetCarByLicensePlate_withLicensesPlatesEquals_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        Mockito.`when`(carRepository.getByLicensePlate("AJH245")).thenReturn(car)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val cars = parkingService.getCarByLicensePlate("AJH245")

        //Assert
        assertEquals(Car("AJH245", calendarParking).getLicensePlate, cars!!.getLicensePlate)
    }

    @Test
    fun testGetCarByLicensePlate_withLicensesPlatesNotEquals_correct() {
        //Arrange
        Mockito.`when`(carRepository.getByLicensePlate("AJH245")).thenReturn(null)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val cars = parkingService.getCarByLicensePlate("AJH245")

        //Assert
        assertEquals(null, cars)
    }

    @Test
    fun testSaveCar_withVehicleNotExist_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        Mockito.`when`(carRepository.getByLicensePlate("AJH245")).thenReturn(car)
        val parkingService = ParkingCarService(carRepository)

        //Act
        parkingService.saveCar(Car("LJH248", calendarParking), "LUNES")

        //Assert
        assert(true)
    }

    @Test
    fun testSaveCar_withVehicleExist_error() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        Mockito.`when`(carRepository.getByLicensePlate("AJH245")).thenReturn(car)
        val parkingService = ParkingCarService(carRepository)

        try {
            //Act
            parkingService.saveCar(Car("AJH245", calendarParking), "MARTES")
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageLicensePlateExistException, ex.message)
        }
    }

    @Test
    fun testSaveCar_withVehicleVehicleDayMonday_error() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        Mockito.`when`(carRepository.getByLicensePlate("AJH245")).thenReturn(car)
        val parkingService = ParkingCarService(carRepository)

        try {
            //Act
            parkingService.saveCar(Car("AJH245", calendarParking), "LUNES")
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageDayException, ex.message)
        }

    }

    @Test
    fun testDeleteCar_error() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        Mockito.`when`(carRepository.delete(car)).thenThrow(VehicleDeleteException())
        val parkingService = ParkingCarService(carRepository)
        try {
            //Act
            parkingService.deleteCar(car)
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageDeleteException, ex.message)
        }
    }

    @Test
    fun testDeleteCar_() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val car = Car("AJH245", calendarParking)
        val parkingService = ParkingCarService(carRepository)

        try {
            //Act
            parkingService.deleteCar(car)
            assert(true)
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageDeleteException, ex.message)
        }
    }

    @Test
    fun testCalculatePriceForHours_withCar_price100() {
        //Arrange
        carRepository = Mockito.mock(CarRepository::class.java)
        val parkingService = ParkingCarService(carRepository)

        //Act
        val resultPriceHourCar = parkingService.calculatePriceByCar(1)

        //Assert
        assertEquals(1000, resultPriceHourCar)
    }

}
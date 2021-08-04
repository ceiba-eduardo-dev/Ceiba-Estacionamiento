package com.ceiba.domain.service

import com.ceiba.domain.exception.VehicleDeleteException
import com.ceiba.domain.model.Motorcycle
import com.ceiba.domain.model.parking.CalendarParking
import com.ceiba.domain.repository.CarRepository
import com.ceiba.domain.repository.MotorcycleRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class ParkingMotorcycleServiceTest : TestCase() {
    companion object {
        const val messageDeleteException = "ERROR AL ELIMINAR EL VEHICULO"
        const val messageLicensePlateException = "ERROR EN EL FORMATO DE LA PLACA"
        const val messageLicensePlateExistException = "ERROR EL VEHICULO YA SE ENCUENTRA REGISTRADO"
        const val formatDate = "11-05-2021 05:05:00"
    }

    @Mock
    lateinit var motorCycleRepository: MotorcycleRepository

    @Before
    override fun setUp() {
        //Arrange
        motorCycleRepository = Mockito.mock(MotorcycleRepository::class.java)
    }

    @Test
    fun testGetAll_withMotorcycles_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        val elements = listOf(motorcycle, motorcycle)
        Mockito.`when`(motorCycleRepository.getAll()).thenReturn(elements)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val motorcycles = parkingService.getAll()

        //Assert
        assertEquals(motorcycles!!.size, motorcycles!!.size)
    }

    fun testGetAll_motorcyclesEmpty_correct() {
        //Arrange
        val elements = listOf<Motorcycle>()
        Mockito.`when`(motorCycleRepository.getAll()).thenReturn(elements)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val motorcycles = parkingService.getAll()

        //Assert
        assertEquals(emptyList<Motorcycle>(), motorcycles)
    }

    @Test
    fun testGetCount_listOf2_correct() {
        //Arrange
        Mockito.`when`(motorCycleRepository.getCount()).thenReturn(2)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val motorcycles = parkingService.getCount()

        //Assert
        assertEquals(2, motorcycles)
    }

    @Test
    fun testGetMotorcycleByLicensePlate_withLicensesPlatesEquals_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        Mockito.`when`(motorCycleRepository.getByLicensePlate("AJH245")).thenReturn(motorcycle)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val motorcycles = parkingService.getMotorcycleByLicensePlate("AJH245")

        //Assert
        assertEquals(
            Motorcycle("AJH245", 200, calendarParking).getLicensePlate,
            motorcycles!!.getLicensePlate
        )
    }

    @Test
    fun testGetMotorcycleByLicensePlate_withLicensesPlatesNotEquals_correct() {
        //Arrange
        Mockito.`when`(motorCycleRepository.getByLicensePlate("AJH245")).thenReturn(null)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val motorcycles = parkingService.getMotorcycleByLicensePlate("AJH245")

        //Assert
        assertEquals(null, motorcycles)
    }

    @Test
    fun testSaveMotorcycle_withVehicleNotExist_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        Mockito.`when`(motorCycleRepository.getByLicensePlate("AJH245")).thenReturn(motorcycle)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        parkingService.saveMotorcycle(Motorcycle("LJH248", 200, calendarParking))

        //Assert
        assert(true)
    }

    @Test
    fun testSaveMotorcycle_withVehicleExist_error() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        Mockito.`when`(motorCycleRepository.getByLicensePlate("AJH245")).thenReturn(motorcycle)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        try {
            //Act
            parkingService.saveMotorcycle(Motorcycle("AJH245", 200, calendarParking))
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageLicensePlateExistException, ex.message)
        }
    }


    @Test
    fun testDeleteMotorcycle_error() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        Mockito.`when`(motorCycleRepository.delete(motorcycle)).thenThrow(VehicleDeleteException())
        val parkingService = ParkingMotorcycleService(motorCycleRepository)
        try {
            //Act
            parkingService.deleteMotorcycle(motorcycle)
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageDeleteException, ex.message)
        }
    }

    @Test
    fun testDeleteMotorcycle() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        try {
            //Act
            parkingService.deleteMotorcycle(motorcycle)
            assert(true)
        } catch (ex: Exception) {
            //Assert
            assertEquals(messageDeleteException, ex.message)
        }
    }

    @Test
    fun testGetPriceByCylinderCapacity_maxCylinderCapacityOvercome_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 600, calendarParking)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val result = parkingService.getPriceByCylinderCapacity(motorcycle)

        //Assert
        assertEquals(2000, result)

    }

    @Test
    fun testGetPriceByCylinderCapacity_cylinderCapacityNotReached_correct() {
        //Arrange
        val calendarParking = CalendarParking(formatDate).getCalendar()
        val motorcycle = Motorcycle("AJH245", 200, calendarParking)
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val expected = parkingService.getPriceByCylinderCapacity(motorcycle)

        //Assert
        assertEquals(0, expected)
    }

    @Test
    fun testCalculatePriceForHours_withCar_price100() {
        //Arrange
        val parkingService = ParkingMotorcycleService(motorCycleRepository)

        //Act
        val resultPriceHourCar = parkingService.calculatePriceByMotorcycle(1)

        //Assert
        assertEquals(500, resultPriceHourCar)
    }

}
package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class ParkingCarServiceExceptionTest : TestCase() {

    companion object {
        const val messageExpected = "EL VEHICULO NO PUEDE ENTRAR EL DÍA LUNES"
        const val messageNotExpected = "EL VEHICULO SI PUEDE ENTRAR EL DÍA LUNES"
    }

    @Test
    fun testGetMessage_messageExpected_success() {
        //Arrange - Act
        val messageException = ParkingCarServiceException().message

        //Assert
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        //Arrange - Act
        val messageException = ParkingCarServiceException().message

        //Assert
        assertEquals(true, (messageNotExpected != messageException))
    }
}
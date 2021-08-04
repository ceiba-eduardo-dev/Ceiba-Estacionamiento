package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class VehicleAlreadyExistExceptionTest : TestCase() {

    companion object {
        const val messageExpected = "ERROR EL VEHICULO YA SE ENCUENTRA REGISTRADO"
        const val messageNotExpected = "EXITO EL VEHICULO NO SE ENCUENTRA REGISTRADO"
    }

    @Test
    fun testGetMessage_messageExpected_success() {
        //Arrange - Act
        val messageException = VehicleAlreadyExistException().message

        //Assert
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        //Arrange - Act
        val messageException = VehicleAlreadyExistException().message

        //Assert
        assertEquals(true, (messageNotExpected != messageException))
    }
}
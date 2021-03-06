package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class CalendarParkingExceptionTest : TestCase() {

    companion object {
        const val messageExpected = "ERROR AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"
        const val messageNotExpected = "EXITO AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"
    }

    @Test
    fun testGetMessage_messageExpected_success() {
        //Arrange - Act
        val messageException = CalendarParkingException().message
        
        //Assert
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        //Arrange - Act
        val messageException = CalendarParkingException().message

        //Assert
        assertEquals(true, (messageNotExpected != messageException))
    }
}
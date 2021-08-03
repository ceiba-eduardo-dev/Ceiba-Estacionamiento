package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class ParkingCarServiceExceptionTest : TestCase() {
    private val messageExpected = "EL VEHICULO NO PUEDE ENTRAR EL DÍA LUNES"

    @Test
    fun testGetMessage_messageExpected_success() {
        val messageException = ParkingCarServiceException().message
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        val messageException = ParkingCarServiceException().message
        val messageNotExpected = "EL VEHICULO SI PUEDE ENTRAR EL DÍA LUNES"
        assertEquals(true, (messageNotExpected != messageException))
    }
}
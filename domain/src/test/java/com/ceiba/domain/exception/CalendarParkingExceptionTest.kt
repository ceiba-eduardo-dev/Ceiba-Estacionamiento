package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class CalendarParkingExceptionTest : TestCase() {

    private val messageExpected = "ERROR AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"

    @Test
    fun testGetMessage_messageExpected_success() {
        val messageException = CalendarParkingException().message
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        val messageException = CalendarParkingException().message
        val messageNotExpected = "EXITO AL CONVERTIR LA CEDENA DE TEXTO EN UNA FECHA"
        assertEquals(true, (messageNotExpected != messageException))
    }
}
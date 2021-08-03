package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test


class InvalidLicensePlateExceptionTest : TestCase() {

    private val messageExpected = "ERROR EN EL FORMATO DE LA PLACA"

    @Test
    fun testGetMessage_messageExpected_success() {
        val messageException = InvalidLicensePlateException().message
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        val messageException = InvalidLicensePlateException().message
        val messageNotExpected = "FORMATO DE LA PLACA CORRECTO"
        assertEquals(true, (messageNotExpected != messageException))
    }
}
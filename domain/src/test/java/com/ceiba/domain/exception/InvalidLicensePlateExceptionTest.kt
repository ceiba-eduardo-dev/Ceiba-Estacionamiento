package com.ceiba.domain.exception

import junit.framework.TestCase

import org.junit.Test

import org.junit.Assert.*

class InvalidLicensePlateExceptionTest : TestCase() {

    companion object {
        const val messageExpected = "ERROR EN EL FORMATO DE LA PLACA"
        const val messageNotExpected = "LA PLACA ES CORRECTA"
    }

    @Test
    fun testGetMessage_messageExpected_success() {
        //Arrange - Act
        val messageException = InvalidLicensePlateException().message

        //Assert
        assertEquals(messageExpected, messageException)
    }

    @Test
    fun testGetMessage_messageNoExpected_success() {
        //Arrange - Act
        val messageException = InvalidLicensePlateException().message

        //Assert
        assertEquals(true, (messageNotExpected != messageException))
    }
}
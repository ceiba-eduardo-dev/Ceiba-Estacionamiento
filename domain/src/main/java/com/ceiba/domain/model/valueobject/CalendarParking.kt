package com.ceiba.domain.model.valueobject

import com.ceiba.domain.exception.CalendarParkingException
import java.text.SimpleDateFormat
import java.util.*

class CalendarParking(
    private val dateString: String
) {
    private lateinit var calendar: Calendar

    companion object {
        val formatDate = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
    }

    fun getCalendar(): Calendar {
        return try {
            val dateResult = Calendar.getInstance()
            val temporalDate: Date = formatDate.parse(dateString)
            dateResult.time = temporalDate
            dateResult
        } catch (exception: Exception) {
            throw CalendarParkingException(exception)
        }
    }
}
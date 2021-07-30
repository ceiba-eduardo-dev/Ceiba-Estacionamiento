package com.ceiba.estacionamiento.vehicles.model.vo

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class DatePeriod(
    private val dateInitial: String,
    val dateEnd: String,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun totalHoursByDate(): Long {
        val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val start = LocalDate.parse("$dateInitial", firstApiFormat)
        val end = LocalDate.parse("$dateEnd", firstApiFormat)
        return ChronoUnit.DAYS.between(start, end) * 24
    }
}
package com.ceiba.estacionamiento.viewcomponent

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerComponent(
    val lHour: (date: String) -> Unit
) :
    DialogFragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var date: String = ""
    var hourTime: String = ""

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        var dateTemp = ""
        dateTemp += if (day < 10) {
            "0$day"
        } else {
            day.toString()
        }
        dateTemp += "-"
        dateTemp += if (month < 10) {
            "0$month"
        } else {
            day.toString()
        }
        dateTemp += "-$year"
        date = dateTemp
        TimePickerDialog(view?.context, this, hour, 0, true).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(activity as Context, this, year, month, day)
        picker.datePicker.minDate = c.timeInMillis
        return picker
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        var hour = ""
        hour += if (hourOfDay < 10) {
            "0$hourOfDay"
        } else {
            hourOfDay.toString()
        }
        hour += ":"
        hour += if (minute < 10) {
            "0$minute"
        } else {
            minute.toString()
        }
        hour += ":00"
        hourTime = hour
        lHour( "$date $hourTime")
    }

}
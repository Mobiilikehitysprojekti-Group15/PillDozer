package com.example.pilldozer

import android.os.Build
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class TimePickerClass {


    fun getTimePickerHour(tp: TimePicker): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tp.hour
        } else {
            tp.currentHour
        }
    }

    fun getTimePickerMinute(tp: TimePicker): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tp.minute
        } else {
            tp.currentMinute
        }
    }




/*
    @RequiresApi(Build.VERSION_CODES.O)
    public fun dateTimePicker() {

        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()

        val dateFormatter = DateTimeFormatter.BASIC_ISO_DATE
        val dateFormatted = currentDate.format(dateFormatter)

        val timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
        val timeFormatted = currentTime.format(timeFormatter)

        println("Current Date is: $dateFormatted")
        println("Current Time is: $timeFormatted")
    }*/
}
package com.shubham.todolist.utils

import java.text.DecimalFormat
import java.text.NumberFormat

object TimeUtils {

    fun formatDigitPlaces(hours: Int, minute: Int): String{
        val f: NumberFormat = DecimalFormat("00")
        return "${f.format(hours)}:${f.format(minute)}"
    }

    fun compareTimeStampIfPast(timeStamp:Long) : Boolean{
        if (System.currentTimeMillis() >= timeStamp){
            return true
        }
        return false
    }

    fun getIfTimeIsPast12(hourOfDay : Int) : String {
        return if (hourOfDay >= 12) "PM" else "AM"
    }
}
package com.bogarsoft.personalnote.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class Helper {
    companion object{
        //convert date to formatted string using datetimeformatter
        @SuppressLint("NewApi")
        fun dateToString(date: Date,format :String = "dd MMM yyyy"):String{
            return SimpleDateFormat(format, Locale.getDefault()).format(date)
        }
    }
}
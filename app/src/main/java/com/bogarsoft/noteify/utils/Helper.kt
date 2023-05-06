package com.bogarsoft.noteify.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Helper {
    companion object{
        //convert date to formatted string using datetimeformatter
        @SuppressLint("NewApi")
        fun dateToString(date: Date,format :String = "dd MMM yyyy"):String{
            return SimpleDateFormat(format, Locale.getDefault()).format(date)
        }

        //check if string contains link if so exract it
        fun extractLink(text: String):String?{
            val regex = Regex(pattern = "(http|https)://[^\\s]+")
            val matchResult = regex.find(text)
            return matchResult?.value
        }

        //extract metadata from link
    }
}
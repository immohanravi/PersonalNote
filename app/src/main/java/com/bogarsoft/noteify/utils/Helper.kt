package com.bogarsoft.noteify.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.bogarsoft.noteify.interfaces.OnOpenGraphResponse
import com.kedia.ogparser.CacheProvider
import com.kedia.ogparser.OpenGraphCallback
import com.kedia.ogparser.OpenGraphParser
import com.kedia.ogparser.OpenGraphResult
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Helper {
    companion object{

        private const val TAG = "Helper"
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

        fun getOpenGraph(url:String, context: Context, openGraphResponse: OnOpenGraphResponse){
            val opengraph = OpenGraphParser(object : OpenGraphCallback {
                override fun onError(error: String) {
                    Log.d(TAG, "onError: "+error)
                    openGraphResponse.error(error)
                }

                override fun onPostResponse(openGraphResult: OpenGraphResult) {
                    Log.d(TAG, "onPostResponse: "+openGraphResult)
                    openGraphResponse.response(openGraphResult)
                }

            })

            opengraph.parse(url)

        }

        //extract metadata from link
    }

}
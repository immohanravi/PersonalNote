package com.bogarsoft.noteify.interfaces

import com.kedia.ogparser.OpenGraphResult

interface OnOpenGraphResponse {

    fun response(openGraphResult: OpenGraphResult)
    fun error(error:String)
}
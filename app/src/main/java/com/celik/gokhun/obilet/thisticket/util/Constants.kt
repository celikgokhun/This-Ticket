package com.celik.gokhun.obilet.thisticket.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val BASE_URL = "https://v2-api.obilet.com/api/"
    const val API_KEY = "JEcYcEMyantZV095WVc3G2JtVjNZbWx1"

}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate():String{
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return sdf.format(Date())
}

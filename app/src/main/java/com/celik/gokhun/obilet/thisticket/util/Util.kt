package com.celik.gokhun.obilet.thisticket.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*



object Constants {
    const val BASE_URL = "https://v2-api.obilet.com/api/"
    const val API_KEY = "JEcYcEMyantZV095WVc3G2JtVjNZbWx1"

    //var sessionId = " "
    //var deviceId = " "
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDateTime():String{
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return sdf.format(Date())
}

fun getCurrentDate():String{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
}

fun getCurrentDateTomorrow():String{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(Date())
}

fun getCurrentDateWithFineFormat():String{
    val sdf = SimpleDateFormat("dd MM yyyy EE")
    return sdf.format(Date())
}

fun getCurrentDateWithFineFormatTomorrow():String{
    val sdf = SimpleDateFormat("dd MM yyyy EE")
    var dt = Date()
    val c = Calendar.getInstance()
    c.time = dt
    c.add(Calendar.DATE, 1)
    dt = c.time
    return sdf.format(dt)
}


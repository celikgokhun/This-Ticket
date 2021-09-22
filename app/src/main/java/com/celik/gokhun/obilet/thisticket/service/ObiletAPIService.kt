package com.celik.gokhun.obilet.thisticket.service

import android.annotation.SuppressLint
import com.celik.gokhun.obilet.thisticket.model.*
import com.celik.gokhun.obilet.thisticket.util.Constants.BASE_URL
import com.celik.gokhun.obilet.thisticket.util.getCurrentDate
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class ObiletAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ObiletAPI::class.java)

    fun getSession() : Single<Session> {
        val idle = Idle()
        val sessionRequestApplication = SessionRequestApplication("3.1.0.0", "dsadas")
        val sessionRequest  = SessionRequest(3, idle , sessionRequestApplication)

        //val gson = Gson()
        //println(": session  :"+ gson.toJson(sessionRequest))

        return api.getSession(
            sessionRequest
        )
    }

    fun getBusLocations(sessionId: String, deviceId: String) : Single<BusLocations> {
        val language = Locale.getDefault().language +"-"+ Locale.getDefault().language.toUpperCase()
        val busLocationsRequestDeviceSession = BusLocationsRequestDeviceSession(sessionId,deviceId)
        val busLocationsRequest = BusLocationsRequest("", busLocationsRequestDeviceSession,getCurrentDate(),language)

        //val gson = Gson()
        //println("Yarrak:   "+ gson.toJson(busLocationsRequest))

        return api.getBusLocations(
            busLocationsRequest
        )
    }




}
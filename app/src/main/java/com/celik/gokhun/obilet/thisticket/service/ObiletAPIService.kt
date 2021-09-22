package com.celik.gokhun.obilet.thisticket.service

import com.celik.gokhun.obilet.thisticket.model.*
import com.celik.gokhun.obilet.thisticket.util.Constants.BASE_URL
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ObiletAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ObiletAPI::class.java)

    fun getSession() : Single<Session> {
        val idle = Idle()
        val sessionRequestApplication = SessionRequestApplication("3.1.0.0", "DD2A0857-7C7D-4376-A83B-E045435E82BB")
        val sessionRequest  = SessionRequest(3, idle , sessionRequestApplication)

        //val gson = Gson()
        //println(": session  :"+ gson.toJson(sessionRequest))

        return api.getSession(
            sessionRequest
        )
    }

    fun getBusLocations(sessionId: String, deviceId: String) : Single<BusLocations> {

        //val busLocationsRequestDeviceSession = BusLocationsRequestDeviceSession(sessionId,deviceId)
        val busLocationsRequestDeviceSession = BusLocationsRequestDeviceSession(sessionId,deviceId)
        val busLocationsRequest = BusLocationsRequest("", busLocationsRequestDeviceSession,"2021-03-11T11:33:00","tr-TR")

        //val gson = Gson()
        //println("Yarrak:   "+ gson.toJson(busLocationsRequest))

        return api.getBusLocations(
            busLocationsRequest
        )
    }
}
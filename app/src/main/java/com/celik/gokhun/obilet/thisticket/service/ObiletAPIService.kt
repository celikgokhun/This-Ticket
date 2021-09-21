package com.celik.gokhun.obilet.thisticket.service

import com.celik.gokhun.obilet.thisticket.model.*
import com.celik.gokhun.obilet.thisticket.util.Constants.BASE_URL
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
        val sessionRequestApplication = SessionRequestApplication("3.1.0.0", "DD2A0857-7C7D-4376-A83B-E045435E82BB")
        val sessionRequest  = SessionRequest(3, sessionRequestApplication , sessionRequestApplication)

        return api.getSession(
            sessionRequest
        )
    }

    fun getBusLocations() : Single<List<BusLocations>> {
        val busLocationsRequestDeviceSession = BusLocationsRequestDeviceSession("PqtdftjloK3Kpka97+ILDzMa6D9740nggLiTzXiLlzA=","PqtdftjloK3Kpka97+ILDzMa6D9740nggLiTzXiLlzA=")
        val busLocationsRequest = BusLocationsRequest(null, busLocationsRequestDeviceSession,"2016-03-11T11:33:00","tr-TR")

        return api.getBusLocations(
            busLocationsRequest
        )
    }
}
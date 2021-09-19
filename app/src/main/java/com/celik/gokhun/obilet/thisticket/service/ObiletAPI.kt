package com.celik.gokhun.obilet.thisticket.service

import com.celik.gokhun.obilet.thisticket.model.BusLocations
import com.celik.gokhun.obilet.thisticket.model.Session
import com.celik.gokhun.obilet.thisticket.util.Constants.API_KEY
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ObiletAPI {


    @Headers("Authorization: $API_KEY\"", "Content-Type: application/json")
    @POST("client/getsession")
    fun getSession(): Single<Session>

    @Headers("Authorization: $API_KEY\"", "Content-Type: application/json")
    @POST("location/getbuslocations")
    fun getBusLocations(): Single<List<BusLocations>>



}
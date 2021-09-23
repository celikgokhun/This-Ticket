package com.celik.gokhun.obilet.thisticket.service

import com.celik.gokhun.obilet.thisticket.model.*
import com.celik.gokhun.obilet.thisticket.util.Constants.API_KEY
import io.reactivex.Single
import retrofit2.http.*

interface ObiletAPI {

    @POST("client/getsession")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic $API_KEY"
    )
    fun getSession(
        @Body sessionRequest: SessionRequest
    ): Single<Session>


    @POST("location/getbuslocations")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic $API_KEY"
    )
    fun getBusLocations(
        @Body busLocationsRequest: BusLocationsRequest
    ): Single<BusLocations>



    @POST("journey/getbusjourneys")
    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic $API_KEY"
    )
    fun getBusJourneys(
        @Body busJourneysRequest: BusJourneysRequest
    ): Single<BusJourneys>





}
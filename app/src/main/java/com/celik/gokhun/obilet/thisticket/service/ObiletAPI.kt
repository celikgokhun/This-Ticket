package com.celik.gokhun.obilet.thisticket.service

import com.celik.gokhun.obilet.thisticket.model.BusLocations
import com.celik.gokhun.obilet.thisticket.model.Session
import com.celik.gokhun.obilet.thisticket.model.SessionRequest
import com.celik.gokhun.obilet.thisticket.util.Constants.API_KEY
import com.celik.gokhun.obilet.thisticket.util.Constants.CALL_ATTRIBUTES
import io.reactivex.Single
import retrofit2.http.*

interface ObiletAPI {


    @POST("client/getsession")
    @Headers(
        //"Accept: application/json",
        "Authorization: Basic JEcYcEMyantZV095WVc3G2JtVjNZbWx1",
        "Content-Type: application/json"
    )
    fun getSession(
        @Body body: SessionRequest
    ): Single<Session>




    @Headers("Authorization: $API_KEY\"", CALL_ATTRIBUTES)
    @POST("location/getbuslocations")
    fun getBusLocations(): Single<List<BusLocations>>


}
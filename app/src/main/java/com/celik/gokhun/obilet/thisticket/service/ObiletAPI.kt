package com.celik.gokhun.obilet.thisticket.service

import com.celik.gokhun.obilet.thisticket.model.BusLocations
import com.celik.gokhun.obilet.thisticket.model.Session
import com.celik.gokhun.obilet.thisticket.util.Constants.API_KEY
import com.celik.gokhun.obilet.thisticket.util.Constants.CALL_ATTRIBUTES
import io.reactivex.Single
import retrofit2.http.*

interface ObiletAPI {


    //@Headers("Authorization:Basic $API_KEY", CALL_ATTRIBUTES)
    //@POST("client/getsession")

    @Headers("Authorization: Basic JEcYcEMyantZV095WVc3G2JtVjNZbWx1", "Content-Type: application/json")
    @POST("client/getsession")
    fun getSession(
        @Body body: String
    ): Single<Session>




    @Headers("Authorization: $API_KEY\"", CALL_ATTRIBUTES)
    @POST("location/getbuslocations")
    fun getBusLocations(): Single<List<BusLocations>>


}
package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusLocations(

    @SerializedName("status")
    val status: String?,

    @SerializedName("data")
    val data: List<BusLocationsData?>,

    @SerializedName("message")
    val message: Any?,

    @SerializedName("user-message")
    val userMessage: Any?,

    @SerializedName("api-request-id")
    val apiRequestId: Any?,

    @SerializedName("controller")
    val controller: String?,

    @SerializedName("client-request-id")
    val clientRequestId: Any?
    )

data class BusLocationsData(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("parent-id")
    val parentId: Int?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("geo-location")
    val geoLocation: BusLocationsDataGeoLocation?,

    @SerializedName("zoom")
    val zoom: Int?,

    @SerializedName("tz-code")
    val timeZone: String?,

    @SerializedName("weather-code")
    val weatherCode: Any?,

    @SerializedName("rank")
    val rank: Any?,

    @SerializedName("reference-code")
    val referenceCode: Any?,

    @SerializedName("keywords")
    val keywords: String?


    )

data class BusLocationsDataGeoLocation(
    @SerializedName("latitude")
    val latitude: Double?,

    @SerializedName("longitude")
    val longitude: Double?,

    @SerializedName("zoom")
    val zoom: Int?

    )

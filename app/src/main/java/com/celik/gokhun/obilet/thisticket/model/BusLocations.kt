package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusLocations(

    @SerializedName("status")
    val busLocationsStatus: String?,

    @SerializedName("data")
    val busLocationsData: BusLocationsData?,

    @SerializedName("message")
    val busLocationsMessage: String?,

    @SerializedName("user-message")
    val busLocationsUserMessage: String?,

    @SerializedName("api-request-id")
    val busLocationsApiRequestId: String?,

    @SerializedName("controller")
    val busLocationsController: String?,
)
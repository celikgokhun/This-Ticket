package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusLocationsRequest(

    @SerializedName("data")
    val data: Any?,

    @SerializedName("device-session")
    val deviceSession: BusLocationsRequestDeviceSession?,

    @SerializedName("date")
    val date: String?,

    @SerializedName("language")
    val language: String?
)

data class BusLocationsRequestDeviceSession(

    @SerializedName("session-id")
    val sessionId: String?,

    @SerializedName("device-id")
    val deviceId: String?

)

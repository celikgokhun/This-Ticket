package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusJourneysRequest(

    @SerializedName("device-session")
    val deviceSession: BusJourneysRequestDeviceSession,

    @SerializedName("date")
    val date: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("data")
    val data: BusJourneysRequestData?

)

data class BusJourneysRequestDeviceSession(

    @SerializedName("session-id")
    val sessionId: String?,

    @SerializedName("device-id")
    val deviceId: String?

)

data class BusJourneysRequestData(

    @SerializedName("origin-id")
    val originId: Int?,

    @SerializedName("destination-id")
    val destinationId: Int?,

    @SerializedName("departure-date")
    val departureDate: String?

)

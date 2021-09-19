package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class SessionData(
    @SerializedName("device-id")
    val deviceId: String,

    @SerializedName("session-id")
    val sessionId: String
)
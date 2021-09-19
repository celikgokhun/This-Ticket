package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("api-request-id")
    val apiRequestId: Any,

    @SerializedName("controller")
    val controller: Any,

    @SerializedName("data")
    val sessionData: SessionData,

    @SerializedName("message")
    val message: Any,

    @SerializedName("status")
    val status: String,

    @SerializedName("user-message")
    val userMessage: Any
)
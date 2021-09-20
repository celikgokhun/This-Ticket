package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class Session(

    @SerializedName("status")
    val sessionStatus: String?,

    @SerializedName("data")
    val sessionData: SessionData?,

    @SerializedName("message")
    val sessionMessage: Any?,

    @SerializedName("user-message")
    val sessionUserMessage: Any?,

    @SerializedName("api-request-id")
    val sessionApiRequestId: Any?,

    @SerializedName("controller")
    val sessionController: Any?,

    @SerializedName("client-request-id")
    val sessionClientRequestId: Any?

)
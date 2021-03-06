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

data class SessionData(

    @SerializedName("session-id")
    val sessionDataSessionId: String?,

    @SerializedName("device-id")
    val sessionDataDeviceId: String?,

    @SerializedName("affiliate")
    val sessionDataAffiliate: Any?,

    @SerializedName("device-type")
    val sessionDataDeviceType: Int?,

    @SerializedName("device")
    val sessionDataDevice: Any?



)
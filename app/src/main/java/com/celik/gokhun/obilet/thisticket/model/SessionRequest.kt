package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class SessionRequest(
    @SerializedName("type")
    val type: Int?,

    @SerializedName("connection")
    val connection: Any?,

    @SerializedName("application")
    val application: SessionRequestApplication?

    )

data class SessionRequestApplication(

    @SerializedName("version")
    val version: String?,

    @SerializedName("equipment-id")
    val equipmentId: String?
)


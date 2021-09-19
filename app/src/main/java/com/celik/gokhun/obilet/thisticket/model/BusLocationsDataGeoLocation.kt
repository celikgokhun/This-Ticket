package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusLocationsDataGeoLocation(
    @SerializedName("latitude")
    val busLocationsDataId: Double?,

    @SerializedName("longitude")
    val busLocationsDataParentId: Double?,

    @SerializedName("zoom")
    val busLocationsDataType: String?,

    )

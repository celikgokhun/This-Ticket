package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusLocationsData(
    @SerializedName("id")
    val busLocationsDataId: Int?,

    @SerializedName("parent-id")
    val busLocationsDataParentId: Int?,

    @SerializedName("type")
    val busLocationsDataType: String?,

    @SerializedName("name")
    val busLocationsDataName: String?,

    @SerializedName("geo-location")
    val busLocationsDataGeoLocation: BusLocationsDataGeoLocation?,

    @SerializedName("tz-code")
    val busLocationsDataTimeZone: String?,

    @SerializedName("weather-code")
    val busLocationsDataWeatherCode: Any?,

    @SerializedName("rank")
    val busLocationsDataRank: Int?,

    @SerializedName("reference-code")
    val busLocationsDataReferenceCode: String?,

    @SerializedName("keywords")
    val busLocationsDataKeywords: String?,


    )

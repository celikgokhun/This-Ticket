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

data class BusLocationsDataGeoLocation(
    @SerializedName("latitude")
    val busLocationsDataId: Double?,

    @SerializedName("longitude")
    val busLocationsDataParentId: Double?,

    @SerializedName("zoom")
    val busLocationsDataType: String?,

    )

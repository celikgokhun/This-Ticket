package com.celik.gokhun.obilet.thisticket.model

import com.google.gson.annotations.SerializedName

data class BusJourneys(

    @SerializedName("status")
    val status: String?,

    @SerializedName("data")
    val data: List<BusJourneysData?>,

    @SerializedName("message")
    val message: Any?,

    @SerializedName("user-message")
    val userMessage: Any?,

    @SerializedName("api-request-id")
    val apiRequestId: Any?,

    @SerializedName("controller")
    val controller: String?,

    @SerializedName("client-request-id")
    val clientRequestId: Any?

)

data class BusJourneysData(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("partner-id")
    val partnerId: Int?,

    @SerializedName("partner-name")
    val partnerName: String?,

    @SerializedName("route-id")
    val routeId: Int?,

    @SerializedName("bus-type")
    val busType: String?,

    @SerializedName("bus-type-name")
    val busTypeName: String?,

    @SerializedName("total-seats")
    val totalSeats: Int?,

    @SerializedName("available-seats")
    val availableSeats: Int?,

    @SerializedName("journey")
    val journey: BusJourneysDataJourney?, ///

    @SerializedName("features")
    val features: List<BusJourneysDataFeatures?>, //hfdhfds

    @SerializedName("origin-location")
    val originLocation: String?,

    @SerializedName("destination-location")
    val destinationLocation: String?,

    @SerializedName("is-active")
    val isActive: Boolean?,

    @SerializedName("origin-location-id")
    val originLocationId: Int?,

    @SerializedName("destination-location-id")
    val destinationLocationId: Int?,

    @SerializedName("is-promoted")
    val isPromoted: Boolean?,

    @SerializedName("cancellation-offset")
    val cancellationOffset: Int?,

    @SerializedName("has-bus-shuttle")
    val hasBusShuttle: Boolean?,

    @SerializedName("disable-sales-without-gov-id")
    val disableSalesWithoutGovId: Boolean?,

    @SerializedName("display-offset")
    val displayOffset: String?,

    @SerializedName("partner-rating")
    val partnerRating: Double?,

    @SerializedName("has-dynamic-pricing")
    val hasDynamicPricing: Boolean?,

    @SerializedName("disable-sales-without-hes-code")
    val disableSalesWithoutHesCode: Boolean?,

    @SerializedName("disable-single-seat-selection")
    val disableSingleSeatSelection: Boolean?,

    @SerializedName("change-offset")
    val changeOffset: Int?,

    @SerializedName("rank")
    val rank: Int?,

    @SerializedName("display-coupon-code-input")
    val displayCouponCodeInput: Boolean?

)

data class BusJourneysDataJourney(

    @SerializedName("kind")
    val kind: String?,

    @SerializedName("code")
    val code: String?,

    @SerializedName("stops")
    val stops: List<BusJourneysDataJourneyStops?>,

    @SerializedName("origin")
    val origin: String?,

    @SerializedName("destination")
    val destination: String?,

    @SerializedName("departure")
    val departure: String?,

    @SerializedName("arrival")
    val arrival: String?,

    @SerializedName("currency")
    val currency: String?,

    @SerializedName("duration")
    val duration: String?,

    @SerializedName("original-price")
    val originalPrice: Double?,

    @SerializedName("internet-price")
    val internetPrice: Double?,

    @SerializedName("provider-internet-price")
    val providerInternetPrice: Any?,

    @SerializedName("booking")
    val booking: Any?,

    @SerializedName("bus-name")
    val busName: String?,

    @SerializedName("policy")
    val policy: BusJourneysDataJourneyPolicy?,

    @SerializedName("features")
    val features: Any?,

    @SerializedName("description")
    val description: Any?,

    @SerializedName("available")
    val available: Any?,

    )

data class BusJourneysDataJourneyStops(

    @SerializedName("name")
    val name: String?,

    @SerializedName("station")
    val station: String?,

    @SerializedName("time")
    val time: String?,

    @SerializedName("is-origin")
    val isOrigin: Boolean?,

    @SerializedName("is-destination")
    val isDestination: Boolean?,
)

data class BusJourneysDataJourneyPolicy(

    @SerializedName("max-seats")
    val maxSeats: Any?,

    @SerializedName("max-single")
    val maxSingle: Any?,

    @SerializedName("max-single-males")
    val maxSingleMales: Int?,

    @SerializedName("max-single-females")
    val maxSingleFemales: Int?,

    @SerializedName("mixed-genders")
    val mixedGenders: Boolean?,

    @SerializedName("gov-id")
    val govId: Boolean?,

    @SerializedName("lht")
    val lht: Boolean?,

)

data class BusJourneysDataJourneyFeatures(
    @SerializedName("")
    val one: String?,

    @SerializedName("")
    val two: String?,

    @SerializedName("")
    val three: String?,
)

data class BusJourneysDataFeatures(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("priority")
    val priority: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: Any?,

    @SerializedName("is-promoted")
    val isPromoted: Boolean?,

    @SerializedName("back-color")
    val backColor: Any?,

    @SerializedName("fore-color")
    val foreColor: Any?,





    )

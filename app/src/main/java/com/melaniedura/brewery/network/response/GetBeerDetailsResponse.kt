package com.melaniedura.brewery.network.response

import com.google.gson.annotations.SerializedName
import com.melaniedura.brewery.network.model.BeerDataModel

data class GetBeerDetailsResponse(
    @SerializedName("data")
    val beer: BeerDataModel,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)

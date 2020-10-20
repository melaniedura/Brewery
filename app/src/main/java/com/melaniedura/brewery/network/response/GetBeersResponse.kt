package com.melaniedura.brewery.network.response

import com.google.gson.annotations.SerializedName
import com.melaniedura.brewery.network.model.BeerDataModel

data class GetBeersResponse(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("data")
    val beers: List<BeerDataModel>?,
    @SerializedName("numberOfPages")
    val numberOfPages: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)

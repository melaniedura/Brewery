package com.melaniedura.brewery.network.response

import com.google.gson.annotations.SerializedName
import com.melaniedura.brewery.network.model.StyleDataModel

data class GetStylesResponse(
    @SerializedName("data")
    val data: List<StyleDataModel>,
    @SerializedName("status")
    val status: String,
    @SerializedName("errorMessage")
    val errorMessage: String
)

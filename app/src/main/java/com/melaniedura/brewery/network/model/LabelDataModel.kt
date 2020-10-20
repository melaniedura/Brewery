package com.melaniedura.brewery.network.model

import com.google.gson.annotations.SerializedName

data class LabelDataModel(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("large")
    val large: String
)

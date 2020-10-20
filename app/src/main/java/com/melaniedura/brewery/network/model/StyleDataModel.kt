package com.melaniedura.brewery.network.model

import com.google.gson.annotations.SerializedName
import com.melaniedura.brewery.model.StyleDomainModel

data class StyleDataModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortName")
    val shortName: String?,
    @SerializedName("description")
    val description: String?
)

fun StyleDataModel.toDomainModel() = StyleDomainModel(
    id = this.id,
    name = this.name,
    shortName = this.shortName,
    description = this.description
)

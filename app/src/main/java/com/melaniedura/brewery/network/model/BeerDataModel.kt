package com.melaniedura.brewery.network.model

import com.google.gson.annotations.SerializedName
import com.melaniedura.brewery.model.BeerDomainModel

data class BeerDataModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nameDisplay")
    val nameDisplay: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("labels")
    val labels: LabelDataModel?
)

fun BeerDataModel.toDomainModel(): BeerDomainModel {
    return BeerDomainModel(
        id = this.id,
        name = this.name,
        nameDisplay = this.nameDisplay,
        description = this.description ?: "",
        year = this.year,
        imageSmall = this.labels?.icon ?: "",
        imageMedium = this.labels?.medium ?: "",
        isFavourite = false
    )
}

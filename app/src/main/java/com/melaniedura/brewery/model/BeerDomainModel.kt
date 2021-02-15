package com.melaniedura.brewery.model

import com.melaniedura.brewery.database.BeerEntity

data class BeerDomainModel(
    val id: String,
    val name: String,
    val description: String = "",
    val nameDisplay: String?,
    val year: Int?,
    val imageSmall: String?,
    val imageMedium: String?,
    val isFavourite: Boolean
)

fun BeerDomainModel.toBeerEntity() = BeerEntity(
    id = id,
    name = name,
    description = description,
    nameDisplay = nameDisplay,
    year = year,
    imageSmall = imageSmall,
    imageMedium = imageMedium,
    isFavourite = isFavourite
)

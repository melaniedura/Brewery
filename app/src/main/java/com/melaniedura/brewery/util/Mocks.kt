package com.melaniedura.brewery.util

import com.melaniedura.brewery.database.BeerEntity
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.model.StyleDomainModel

private val mockedBeerStyle = StyleDomainModel(
    id = 1,
    name = "Style name",
    shortName = "Style short name",
    description = "Style description"
)

val mockedBeerStylesList = listOf(
    mockedBeerStyle,
    mockedBeerStyle.copy(id = 2),
    mockedBeerStyle.copy(id = 3))

val mockedBeer = BeerDomainModel(
    id = "a1b2c3",
    name = "Beer name",
    nameDisplay = "Beer name display",
    description = "Description",
    year = 2000,
    imageSmall = "imageSmall",
    imageMedium = "imageMedium",
    isFavourite = false
)

val mockedBeerList = listOf(
    mockedBeer,
    mockedBeer.copy(id = "d4e5f6"),
    mockedBeer.copy(id = "g7h8i9"))

val mockedBeerEntity = BeerEntity(
    id = "a1b2c3",
    name = "Beer name",
    nameDisplay = "Beer name display",
    description = "Description",
    year = 2000,
    imageSmall = "imageSmall",
    imageMedium = "imageMedium",
    isFavourite = false
)

package com.melaniedura.brewery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.melaniedura.brewery.model.BeerDomainModel

@Entity
data class BeerEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nameDisplay") val nameDisplay: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "imageSmall") val imageSmall: String?,
    @ColumnInfo(name = "imageMedium") val imageMedium: String?,
    @ColumnInfo(name = "year") val year: Int?,
    @ColumnInfo(name = "isFavourite") val isFavourite: Boolean = false
)

fun BeerEntity.toDomainModel() = BeerDomainModel(
    description = this.description ?: "",
    id = this.id,
    name = this.name,
    nameDisplay = this.nameDisplay,
    year = this.year,
    imageSmall = this.imageSmall ?: "",
    imageMedium = this.imageMedium ?: "",
    isFavourite = this.isFavourite
)

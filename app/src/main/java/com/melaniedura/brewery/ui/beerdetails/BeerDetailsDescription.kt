package com.melaniedura.brewery.ui.beerdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.melaniedura.brewery.model.BeerDomainModel

@Composable
fun BeerDetailsDescription(viewModel: BeerDetailsViewModel) {
    val beer by viewModel.beer.observeAsState()

    beer?.data?.let {
        BeerDetailContent(beer = it)
    }
}

@Composable
fun BeerDetailContent(beer: BeerDomainModel) {
    BeerDescription(description = beer.description)
}

@Composable
fun BeerDescription(description: String) {
    Text(text = description, modifier = Modifier.padding(16.dp))
}
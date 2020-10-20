package com.melaniedura.brewery.ui.beerstyles

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource

class BeerStylesViewModel @ViewModelInject constructor(private val repository: BreweryRepository) : ViewModel() {

    val beerStyles = liveData {
        emit(Resource.loading(null))
        emit(repository.getBeerStyles())
    }
}

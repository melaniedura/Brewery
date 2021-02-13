package com.melaniedura.brewery.ui.beerstyles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerStylesViewModel @Inject constructor(private val repository: BreweryRepository) :
    ViewModel() {

    val beerStyles = liveData {
        emit(Resource.loading(null))
        emit(repository.getBeerStyles())
    }
}

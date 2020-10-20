package com.melaniedura.brewery.ui.beers

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource

class BeersViewModel @ViewModelInject constructor(private val repository: BreweryRepository) : ViewModel() {

    private val styleId = MutableLiveData<Int>()

    fun getBeers(styleId: Int) {
        this.styleId.value = styleId
    }

    var beers = styleId.switchMap { styleId ->
        liveData {
            emit(Resource.loading(null))
            emit(repository.getBeers(styleId))
        }
    }
}

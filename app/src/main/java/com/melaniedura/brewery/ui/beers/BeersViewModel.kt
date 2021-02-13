package com.melaniedura.brewery.ui.beers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeersViewModel @Inject constructor(private val repository: BreweryRepository) : ViewModel() {

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

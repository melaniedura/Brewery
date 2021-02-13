package com.melaniedura.brewery.ui.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailsViewModel @Inject constructor(private val repository: BreweryRepository) :
    ViewModel() {

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    private val _beer = MutableLiveData<Resource<BeerDomainModel>>()
    val beer: LiveData<Resource<BeerDomainModel>> = _beer

    fun getBeer(id: String) {
        viewModelScope.launch {
            _beer.value = Resource.loading(null)
            _beer.value = repository.getBeer(id)
            updateUi()
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            _beer.value?.data?.let {
                _beer.value = repository.updateBeer(it.copy(isFavourite = !it.isFavourite))
                updateUi()
            }
        }
    }

    private fun updateUi() =
        _beer.value?.data?.run {
            _favorite.value = isFavourite
        }
}

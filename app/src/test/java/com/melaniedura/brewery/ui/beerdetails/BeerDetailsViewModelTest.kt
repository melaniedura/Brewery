package com.melaniedura.brewery.ui.beerdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource
import com.melaniedura.brewery.util.mockedBeer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BeerDetailsViewModelTest {

    private lateinit var viewModel: BeerDetailsViewModel
    private lateinit var breweryRepository: BreweryRepository
    private lateinit var beerObserver: Observer<Resource<BeerDomainModel>>
    private val validBeerId = "a1c2d3"
    private val invalidBeerId = ""

    private val successResource = Resource.success(mockedBeer)
    private val errorResource = Resource.error("Error", null)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        breweryRepository = mock()
        runBlocking {
            whenever(breweryRepository.getBeer(validBeerId)).thenReturn(successResource)
            whenever(breweryRepository.getBeer(invalidBeerId)).thenReturn(errorResource)
        }
        viewModel = BeerDetailsViewModel(breweryRepository)
        beerObserver = mock()
    }

    @Test
    fun `when load beer success`() = runBlocking {
        viewModel.beer.observeForever(beerObserver)
        viewModel.getBeer(validBeerId)
        delay(10)
        verify(breweryRepository).getBeer(validBeerId)
        verify(beerObserver).onChanged(Resource.loading(null))
        verify(beerObserver).onChanged(successResource)
    }

    @Test
    fun `when load beer error`() = runBlocking {
        viewModel.beer.observeForever(beerObserver)
        viewModel.getBeer(invalidBeerId)
        delay(10)
        verify(breweryRepository).getBeer(invalidBeerId)
        verify(beerObserver).onChanged(Resource.loading(null))
        verify(beerObserver).onChanged(errorResource)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}

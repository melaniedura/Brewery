package com.melaniedura.brewery.ui.beers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource
import com.melaniedura.brewery.util.mockedBeerList
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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BeersViewModelTest {

    private lateinit var viewModel: BeersViewModel
    private lateinit var breweryRepository: BreweryRepository
    private lateinit var beersObserver: Observer<Resource<List<BeerDomainModel>>>
    private val validStyleId = 1
    private val invalidStyleId = -1

    private val successResource = Resource.success(mockedBeerList)
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
            whenever(breweryRepository.getBeers(validStyleId)).thenReturn(successResource)
            whenever(breweryRepository.getBeers(invalidStyleId)).thenReturn(errorResource)
        }
        viewModel = BeersViewModel(breweryRepository)
        beersObserver = mock()
    }

    @Test
    fun `when load beers success`() = runBlocking {
        viewModel.beers.observeForever(beersObserver)
        viewModel.getBeers(validStyleId)
        delay(10)
        verify(breweryRepository).getBeers(validStyleId)
        verify(beersObserver).onChanged(Resource.loading(null))
        verify(beersObserver).onChanged(successResource)
    }

    @Test
    fun `when load beers error`() = runBlocking {
        viewModel.beers.observeForever(beersObserver)
        viewModel.getBeers(invalidStyleId)
        delay(10)
        verify(breweryRepository).getBeers(invalidStyleId)
        verify(beersObserver).onChanged(Resource.loading(null))
        verify(beersObserver).onChanged(errorResource)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}

package com.melaniedura.brewery.ui.beerstyles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.melaniedura.brewery.model.StyleDomainModel
import com.melaniedura.brewery.repository.BreweryRepository
import com.melaniedura.brewery.repository.util.Resource
import com.melaniedura.brewery.util.mockedBeerStylesList
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
class BeerStylesViewModelTest {

    private lateinit var viewModel: BeerStylesViewModel
    private lateinit var repository: BreweryRepository
    private lateinit var beerStylesObserver: Observer<Resource<List<StyleDomainModel>>>

    private val successResource = Resource.success(mockedBeerStylesList)

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
        repository = mock()
        runBlocking {
            whenever(repository.getBeerStyles()).thenReturn(successResource)
        }
        viewModel = BeerStylesViewModel(repository)
        beerStylesObserver = mock()
    }

    @Test
    fun `when load beerStyles success`() = runBlocking {
        viewModel.beerStyles.observeForever(beerStylesObserver)
        delay(10)
        verify(repository).getBeerStyles()
        verify(beerStylesObserver).onChanged(Resource.loading(null))
        verify(beerStylesObserver).onChanged(successResource)
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}

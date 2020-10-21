package com.melaniedura.brewery.ui.beerstyles

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaniedura.brewery.R
import com.melaniedura.brewery.model.StyleDomainModel
import com.melaniedura.brewery.repository.util.Status
import com.melaniedura.brewery.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beer_styles.*

@AndroidEntryPoint
class BeerStylesFragment : Fragment(R.layout.fragment_beer_styles) {

    private val viewModel by viewModels<BeerStylesViewModel>()

    private lateinit var beerStylesAdapter: BeerStylesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListAdapter()
        subscribeToObservers()
    }

    private fun setupListAdapter() {
        beerStylesAdapter = BeerStylesAdapter {
            navigateToBeersFragment(it)
        }

        beerStylesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BeerStylesFragment.context)
            adapter = beerStylesAdapter
        }
    }

    private fun navigateToBeersFragment(style: StyleDomainModel) {
        val action = BeerStylesFragmentDirections.actionBeerStylesFragmentToBeersFragment(
            style.id,
            style.shortName ?: style.name
        )
        findNavController().navigate(action)
    }

    private fun subscribeToObservers() {
        viewModel.beerStyles.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.SUCCESS -> setBeerStylesData(resource.data)
                Status.ERROR -> showError(resource.message)
                Status.LOADING -> showLoading()
            }
        })
    }

    private fun setBeerStylesData(data: List<StyleDomainModel>?) {
        if (data.isNullOrEmpty()) {
            showNoResults()
        } else {
            beerStylesAdapter.submitList(data)
        }
        hideLoading()
    }

    private fun showError(message: String?) {
        val errorMessage = message ?: getString(R.string.generic_error_message)
        requireContext().toast("Error $errorMessage")
        hideLoading()
    }

    private fun showNoResults() {
        beerStylesEmptyListText.isVisible = true
    }

    private fun showLoading() {
        beerStylesProgressBar.isVisible = true
    }

    private fun hideLoading() {
        beerStylesProgressBar.isVisible = false
    }
}

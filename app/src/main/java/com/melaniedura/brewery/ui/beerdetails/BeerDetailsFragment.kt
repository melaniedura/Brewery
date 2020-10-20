package com.melaniedura.brewery.ui.beerdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.melaniedura.brewery.R
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.repository.util.Status
import com.melaniedura.brewery.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beer_details.*

@AndroidEntryPoint
class BeerDetailsFragment : Fragment(R.layout.fragment_beer_details) {

    private val viewModel by viewModels<BeerDetailsViewModel>()

    private val args by navArgs<BeerDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
        viewModel.getBeer(args.beerId)
    }

    private fun subscribeToObservers() {
        viewModel.beer.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    loadData(resource.data)
                }
                Status.ERROR -> {
                    showError(resource.message)
                }
                Status.LOADING -> {
                    showLoading()
                }
            }
        })

        viewModel.favorite.observe(viewLifecycleOwner, {
            fab.isSelected = it
        })
    }

    private fun loadData(beerDetails: BeerDomainModel?) {
        if (beerDetails != null) {
            Glide.with(requireContext())
                .load(beerDetails.imageMedium)
                .placeholder(R.mipmap.beer_img)
                .error(R.mipmap.beer_img)
                .fallback(R.mipmap.beer_img)
                .into(beerDetailsImage)

            beerDetailsName.text = beerDetails.name
            beerDetailsDescription.text = beerDetails.description

            fab.isSelected = beerDetails.isFavourite

            fab.setOnClickListener {
                viewModel.toggleFavorite()
            }
        } else {
            showNoResults()
        }
        hideLoading()
    }

    private fun showError(message: String?) {
        val errorMessage = message ?: getString(R.string.generic_error_message)
        requireContext().toast("Error $errorMessage")
        hideLoading()
    }

    private fun showNoResults() {
        beerDetailsEmptyText.isVisible = true
    }

    private fun showLoading() {
        beerDetailsProgressBar.isVisible = true
    }

    private fun hideLoading() {
        beerDetailsProgressBar.isVisible = false
    }
}

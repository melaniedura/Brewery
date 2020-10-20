package com.melaniedura.brewery.ui.beers

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.melaniedura.brewery.R
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.repository.util.Status
import com.melaniedura.brewery.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beers.*

@AndroidEntryPoint
class BeersFragment : Fragment(R.layout.fragment_beers) {

    private val viewModel by viewModels<BeersViewModel>()

    private lateinit var beersAdapter: BeersAdapter

    private val args by navArgs<BeersFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListAdapter()
        subscribeToObservers()

        viewModel.getBeers(args.styleId)
    }

    private fun setupListAdapter() {
        beersAdapter = BeersAdapter {
            val action =
                BeersFragmentDirections.actionBeersFragmentToBeerDetailsFragment(it.id, it.name)
            findNavController().navigate(action)
        }

        beersRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = beersAdapter
        }
    }

    private fun subscribeToObservers() {
        viewModel.beers.observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    setBeersData(resource.data)
                }
                Status.ERROR -> {
                    showError(resource.message)
                }
                Status.LOADING -> {
                    showLoading()
                }
            }
        })
    }

    private fun setBeersData(data: List<BeerDomainModel>?) {
        if (data.isNullOrEmpty()) {
            showNoResults()
        } else {
            beersAdapter.submitList(data)
        }
        hideLoading()
    }

    private fun showError(message: String?) {
        val errorMessage = message ?: getString(R.string.generic_error_message)
        requireContext().toast("Error $errorMessage")
        hideLoading()
    }

    private fun showNoResults() {
        beersEmptyListText.isVisible = true
    }

    private fun showLoading() {
        beersProgressBar.isVisible = true
    }

    private fun hideLoading() {
        beersProgressBar.isVisible = false
    }
}

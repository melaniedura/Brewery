package com.melaniedura.brewery.ui.beers

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melaniedura.brewery.R
import com.melaniedura.brewery.databinding.ItemBeerBinding
import com.melaniedura.brewery.model.BeerDomainModel
import com.melaniedura.brewery.util.viewBinding

class BeersAdapter(private val listener: (BeerDomainModel) -> Unit) :
    ListAdapter<BeerDomainModel, BeersAdapter.BeersViewHolder>(BeerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeersViewHolder(viewBinding(parent, ItemBeerBinding::inflate))

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class BeersViewHolder(private val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: BeerDomainModel, listener: (BeerDomainModel) -> Unit) = with(binding) {
            beerNameText.text = beer.name
            Glide.with(itemView.context)
                .load(beer.imageMedium)
                .centerCrop()
                .placeholder(R.mipmap.beer_img)
                .error(R.mipmap.beer_img)
                .fallback(R.mipmap.beer_img)
                .into(beerImage)
            root.setOnClickListener { listener(beer) }
        }
    }
}

class BeerDiffCallback : DiffUtil.ItemCallback<BeerDomainModel>() {
    override fun areItemsTheSame(oldItem: BeerDomainModel, newItem: BeerDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BeerDomainModel, newItem: BeerDomainModel): Boolean {
        return oldItem == newItem
    }
}

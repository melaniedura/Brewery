package com.melaniedura.brewery.ui.beers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melaniedura.brewery.R
import com.melaniedura.brewery.model.BeerDomainModel
import kotlinx.android.synthetic.main.item_beer.view.*

class BeersAdapter(private val listener: (BeerDomainModel) -> Unit) :
    ListAdapter<BeerDomainModel, BeersAdapter.BeersViewHolder>(BeerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_beer, parent, false))

    override fun onBindViewHolder(holder: BeersViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class BeersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(beer: BeerDomainModel, listener: (BeerDomainModel) -> Unit) = with(itemView) {
            beerNameText.text = beer.name
            Glide.with(context)
                .load(beer.imageMedium)
                .centerCrop()
                .placeholder(R.mipmap.beer_img)
                .error(R.mipmap.beer_img)
                .fallback(R.mipmap.beer_img)
                .into(beerImage)
            setOnClickListener { listener(beer) }
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

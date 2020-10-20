package com.melaniedura.brewery.ui.beerstyles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melaniedura.brewery.R
import com.melaniedura.brewery.model.StyleDomainModel
import kotlinx.android.synthetic.main.item_beer_style.view.*

class BeerStylesAdapter(private val listener: (StyleDomainModel) -> Unit) : ListAdapter<StyleDomainModel, BeerStylesAdapter.BeerStyleViewHolder>(
    BeerStyleDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeerStyleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_beer_style, parent, false))

    override fun onBindViewHolder(holder: BeerStyleViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class BeerStyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stylesData: StyleDomainModel, listener: (StyleDomainModel) -> Unit) {
            itemView.beerStyleText.text = stylesData.name
            itemView.setOnClickListener { listener(stylesData) }
        }
    }
}

class BeerStyleDiffCallback : DiffUtil.ItemCallback<StyleDomainModel>() {
    override fun areItemsTheSame(oldItem: StyleDomainModel, newItem: StyleDomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StyleDomainModel, newItem: StyleDomainModel): Boolean {
        return oldItem == newItem
    }
}

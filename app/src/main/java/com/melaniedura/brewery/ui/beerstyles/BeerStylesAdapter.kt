package com.melaniedura.brewery.ui.beerstyles

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melaniedura.brewery.databinding.ItemBeerStyleBinding
import com.melaniedura.brewery.model.StyleDomainModel
import com.melaniedura.brewery.util.viewBinding

class BeerStylesAdapter(private val listener: (StyleDomainModel) -> Unit) : ListAdapter<StyleDomainModel, BeerStylesAdapter.BeerStyleViewHolder>(
    BeerStyleDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeerStyleViewHolder(viewBinding(parent, ItemBeerStyleBinding::inflate))


    override fun onBindViewHolder(holder: BeerStyleViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class BeerStyleViewHolder(private val binding: ItemBeerStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stylesData: StyleDomainModel, listener: (StyleDomainModel) -> Unit) =
            with(binding) {
                beerStyleText.text = stylesData.name
                root.setOnClickListener { listener(stylesData) }
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

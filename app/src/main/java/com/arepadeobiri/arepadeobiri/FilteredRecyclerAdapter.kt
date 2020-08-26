package com.arepadeobiri.arepadeobiri

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arepadeobiri.arepadeobiri.dataModels.CarOwner
import com.arepadeobiri.arepadeobiri.databinding.ItemFilteredLayoutBinding


class FilteredRecyclerAdapter :
    ListAdapter<CarOwner, FilteredRecyclerAdapter.ViewHolder>(
        FilteredItemInstanceDiffCallback()
    ) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val carOwner = getItem(position)
        holder.bind(carOwner, Util.CHART_COLORS[position%Util.CHART_COLORS.size])
    }


    class ViewHolder private constructor(private val binding: ItemFilteredLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            carOwner: CarOwner,
            color : Int
        ) {
            binding.color = color
            binding.carOwner = carOwner
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemFilteredLayoutBinding.inflate(inflater, parent, false)


                return ViewHolder(binding)
            }
        }
    }


    class FilteredItemInstanceDiffCallback : DiffUtil.ItemCallback<CarOwner>() {
        override fun areItemsTheSame(
            oldItem: CarOwner,
            newItem: CarOwner
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CarOwner,
            newItem: CarOwner
        ): Boolean {
            return oldItem == newItem
        }


    }


}

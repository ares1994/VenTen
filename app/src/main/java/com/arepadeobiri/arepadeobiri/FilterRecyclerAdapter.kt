package com.arepadeobiri.arepadeobiri


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import com.arepadeobiri.arepadeobiri.databinding.ItemFiltersLayoutBinding
import java.util.*


class FilterRecyclerAdapter(private val listener: FilterOnClickedListener) :
    ListAdapter<FilterItem, FilterRecyclerAdapter.ViewHolder>(
        FilterItemInstanceDiffCallback()
    ) {


    interface FilterOnClickedListener {
        fun onClicked()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val filter = getItem(position)
        holder.bind(filter, listener)
    }


    class ViewHolder private constructor(private val binding: ItemFiltersLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            filter: FilterItem,
            listener: FilterOnClickedListener
        ) {
            binding.filter = filter
            binding.listener = listener
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemFiltersLayoutBinding.inflate(inflater, parent, false)


                return ViewHolder(binding)
            }
        }
    }


    class FilterItemInstanceDiffCallback : DiffUtil.ItemCallback<FilterItem>() {
        override fun areItemsTheSame(
            oldItem: FilterItem,
            newItem: FilterItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FilterItem,
            newItem: FilterItem
        ): Boolean {
            return oldItem == newItem
        }


    }


}

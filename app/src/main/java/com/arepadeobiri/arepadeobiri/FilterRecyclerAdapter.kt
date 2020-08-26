package com.arepadeobiri.arepadeobiri


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import kotlinx.android.synthetic.main.item_filters_layout.view.*
import java.util.*


class FilterRecyclerAdapter :
    ListAdapter<FilterItem, FilterRecyclerAdapter.ViewHolder>(
        FilterItemInstanceDiffCallback()
    ) {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_filters_layout, parent, false)


        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val filter = getItem(position)


        holder.apply {
            dateTextView.apply {
                text = context.getString(R.string.date_format,filter.startYear.toString(), filter.endYear.toString())
            }


            genderTextView.apply {
                text = context.getString(R.string.gender_format,if (filter.gender.isNullOrBlank())"N/A" else filter.gender.toLowerCase(Locale.getDefault()).capitalize())
            }


            countriesTextView.apply {
                val countries = filter.countries.toString().drop(1).dropLast(1)

                text = context.getString(R.string.countries_format,if (countries.isBlank())"N/A" else countries)
            }


            colorsTextView.apply {
                val colors = filter.colors.toString().drop(1).dropLast(1)

                text = context.getString(R.string.colors_format,if (colors.isBlank()) "N/A" else colors)
            }



        }


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dateTextView: TextView = itemView.dateTextView

        val colorsTextView: TextView = itemView.colorsTextView

        val countriesTextView: TextView = itemView.countriesTextView

        val genderTextView: TextView = itemView.genderTextView
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

package com.arepadeobiri.arepadeobiri

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import java.util.*

@BindingAdapter("filterDateRange")
fun TextView.dateRange(filter : FilterItem){

    text = context.getString(
        R.string.date_format,
        filter.startYear.toString(),
        filter.endYear.toString()
    )
}


@BindingAdapter("filterGender")
fun TextView.gender(filter : FilterItem){

    text = context.getString(
        R.string.gender_format,
        if (filter.gender.isNullOrBlank()) "N/A" else filter.gender.toLowerCase(
            Locale.getDefault()
        )
            .capitalize()
    )

}



@BindingAdapter("filterCountries")
fun TextView.country(filter : FilterItem){

    val countries = filter.countries.toString().drop(1).dropLast(1)

    text = context.getString(
        R.string.countries_format,
        if (countries.isBlank()) "N/A" else countries
    )

}


@BindingAdapter("filterColors")
fun TextView.color(filter : FilterItem){

    val colors = filter.colors.toString().drop(1).dropLast(1)

    text = context.getString(
        R.string.colors_format,
        if (colors.isBlank()) "N/A" else colors
    )

}


@BindingAdapter("listener")
fun LinearLayout.listener(listener : FilterRecyclerAdapter.FilterOnClickedListener){
    setOnClickListener {
        listener.onClicked()
    }

}
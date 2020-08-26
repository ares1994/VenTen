package com.arepadeobiri.arepadeobiri

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.arepadeobiri.arepadeobiri.dataModels.CarOwner
import com.arepadeobiri.arepadeobiri.dataModels.FilterItem

@BindingAdapter("filterDateRange")
fun TextView.dateRange(filter: FilterItem) {

    text = context.getString(
        R.string.date_format,
        filter.startYear.toString(),
        filter.endYear.toString()
    )
}


@BindingAdapter("filterGender")
fun TextView.gender(filter: FilterItem) {

    text = context.getString(
        R.string.gender_format,
        if (filter.gender.isNullOrBlank()) "N/A" else filter.gender
    )

}


@BindingAdapter("filterCountries")
fun TextView.country(filter: FilterItem) {

    val countries = filter.countries.toString().drop(1).dropLast(1)

    text = context.getString(
        R.string.countries_format,
        if (countries.isBlank()) "N/A" else countries
    )

}


@BindingAdapter("filterColors")
fun TextView.color(filter: FilterItem) {

    val colors = filter.colors.toString().drop(1).dropLast(1)

    text = context.getString(
        R.string.colors_format,
        if (colors.isBlank()) "N/A" else colors
    )

}


@BindingAdapter("name")
fun TextView.name(carOwner: CarOwner) {
    text = context.getString(
        R.string.name_format,
        if (carOwner.firstName.isBlank() && carOwner.lastName.isBlank()) "N/A" else "${carOwner.firstName} ${carOwner.lastName}"
    )

}

@BindingAdapter("email")
fun TextView.email(carOwner: CarOwner) {
    text = context.getString(
        R.string.email_format,
        if (carOwner.email.isBlank()) "N/A" else carOwner.email
    )
}

@BindingAdapter("car_model")
fun TextView.carModel(carOwner: CarOwner) {
    text = context.getString(
        R.string.model_format,
        if (carOwner.carModel.isBlank()) "N/A" else carOwner.carModel
    )
}

@BindingAdapter("car_year")
fun TextView.carYear(carOwner: CarOwner) {
    text = context.getString(
        R.string.year_format,
        if (carOwner.carModelYear.isBlank()) "N/A" else carOwner.carModelYear
    )
}

@BindingAdapter("car_color")
fun TextView.carColor(carOwner: CarOwner) {
    text = context.getString(
        R.string.color_format,
        if (carOwner.color.isBlank()) "N/A" else carOwner.color
    )
}

@BindingAdapter("job")
fun TextView.job(carOwner: CarOwner) {
    text =
        context.getString(R.string.job_format, if (carOwner.job.isBlank()) "N/A" else carOwner.job)
}


@BindingAdapter("country")
fun TextView.country(carOwner: CarOwner) {
    text = context.getString(
        R.string.country_format,
        if (carOwner.country.isBlank()) "N/A" else carOwner.country
    )
}


@BindingAdapter("car_owner_gender")
fun TextView.carOwnerGender(carOwner: CarOwner) {

    text = context.getString(
        R.string.gender_format,
        if (carOwner.gender.isBlank()) "N/A" else carOwner.gender
    )

}


@BindingAdapter("bio")
fun TextView.bio(carOwner: CarOwner) {

    text = if (carOwner.bio.isBlank()) "N/A" else carOwner.bio


}


@BindingAdapter("listener", "filterItem")
fun LinearLayout.listener(
    listener: FilterRecyclerAdapter.FilterOnClickedListener,
    filter: FilterItem
) {
    setOnClickListener {
        listener.onClicked(filter)
    }

}






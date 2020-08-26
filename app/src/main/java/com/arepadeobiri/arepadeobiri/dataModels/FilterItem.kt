package com.arepadeobiri.arepadeobiri.dataModels

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FilterItem (

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("start_year")
	val startYear: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("countries")
	val countries: List<String?>? = null,

	@field:SerializedName("end_year")
	val endYear: Int? = null,

	@field:SerializedName("colors")
	val colors: List<String?>? = null
) : Parcelable



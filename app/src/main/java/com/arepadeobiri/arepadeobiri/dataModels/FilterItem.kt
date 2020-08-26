package com.arepadeobiri.arepadeobiri.dataModels

import com.google.gson.annotations.SerializedName

data class FilterItem(

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
)

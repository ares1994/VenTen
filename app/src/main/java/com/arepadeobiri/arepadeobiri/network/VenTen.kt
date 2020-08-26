package com.arepadeobiri.arepadeobiri.network

import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface VenTen{

    @GET("/assessment/filter.json")
    fun getFiltersAsync(): Deferred<List<FilterItem?>?>


}
package com.arepadeobiri.arepadeobiri.network

import com.arepadeobiri.arepadeobiri.dataModels.FilterItem
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming

interface GoogleDrive {

    @GET("/u/0/uc?id=1giBv3pK6qbOPo0Y02H-wjT9ULPksfBCm&export=download")
    @Streaming
    fun getCsvAsync(): Deferred<ResponseBody>


}
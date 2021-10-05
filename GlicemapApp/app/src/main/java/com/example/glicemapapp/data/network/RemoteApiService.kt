package com.example.contadormtg.networking

import com.example.contadormtg.models.Measurement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("/cards/named?")
    suspend fun getMeasurements(@Query("fuzzy") fuzzy: String) : Response<List<Measurement?>>
}
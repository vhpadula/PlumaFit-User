package com.example.contadormtg.networking

import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.data.models.DateList
import retrofit2.Response
import retrofit2.http.*

interface RemoteApiService {
    @GET("searchMeasures/month")
    suspend fun getMeasurementDates(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<DateList?>

    @GET("searchMeasures/day")
    suspend fun getMeasurementDetails(@Query("documentNumber") documentNumber: String, @Query("date") date: String) : Response<List<MeasurementDetails?>>

    @POST("/cards/named?")
    suspend fun sendNewMeasurement(@Query("documentNumber") documentNumber: String) : Response<String?>

}
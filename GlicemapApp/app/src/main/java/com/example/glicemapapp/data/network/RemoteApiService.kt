package com.example.contadormtg.networking

import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.data.models.DatesResponse
import com.example.glicemapapp.data.models.MeasurementDetailResponse
import com.example.glicemapapp.data.models.SendMeasureRequest
import retrofit2.Response
import retrofit2.http.*

interface RemoteApiService {
    @GET("searchMeasures/month")
    suspend fun getMeasurementDates(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<DatesResponse?>

    @GET("searchMeasures/day")
    suspend fun getMeasurementDetails(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<MeasurementDetailResponse?>

    @POST("postMeasure")
    suspend fun sendMeasurement (@Body request: SendMeasureRequest): Response<Boolean?>
}
package com.example.contadormtg.networking

import android.graphics.pdf.PdfDocument
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.data.models.DatesResponse
import com.example.glicemapapp.data.models.GetUserResponse
import com.example.glicemapapp.data.models.MeasurementDetailResponse
import com.example.glicemapapp.data.models.SendMeasureRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RemoteApiService {
    @GET("searchMeasures/month")
    suspend fun getMeasurementDates(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<DatesResponse?>

    @GET("searchMeasures/day")
    suspend fun getMeasurementDetails(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<MeasurementDetailResponse?>

    @GET("getInfo")
    suspend fun getUser(@Header("documentNumber")documentNumber: String): Response<GetUserResponse?>

    @Streaming
    @GET("exportReport")
    suspend fun getPDF(@Header("documentNumber")documentNumber: String, @Header("dateBegin")dateBegin: String, @Header("dateEnd")dateEmd: String ): Response<ResponseBody?>

    @POST("postMeasure")
    suspend fun sendMeasurement (@Body request: SendMeasureRequest): Response<Boolean?>

}
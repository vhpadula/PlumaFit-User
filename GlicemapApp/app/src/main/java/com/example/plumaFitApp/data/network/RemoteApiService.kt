package com.example.plumaFitApp.data.network

import com.example.plumaFitApp.data.models.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RemoteApiService {
    @GET("measures/month")
    suspend fun getMeasurementDates(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<DatesResponse?>

    @GET("measures/day")
    suspend fun getMeasurementDetails(@Header("documentNumber")documentNumber: String, @Header("date")date: String) : Response<MeasurementDetailResponse?>

    @GET("info")
    suspend fun getUser(@Header("documentNumber")documentNumber: String): Response<GetUserResponse?>

    @Streaming
    @GET("report")
    suspend fun getPDF(@Header("documentNumber")documentNumber: String, @Header("dateBegin")dateBegin: String, @Header("dateEnd")dateEmd: String ): Response<ResponseBody?>

    @POST("measures")
    suspend fun sendMeasurement (@Body request: SendMeasureRequest): Response<Boolean?>

    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): Response<String?>

    @POST("sign-up")
    suspend fun registerUser(@Body request: SignUpRequest): Response<Boolean?>

    @PUT("info")
    suspend fun alterData(@Body request: SignUpRequest): Response<Boolean?>

    @POST("medic")
    suspend fun registerDoctor(@Header("code") code: String,@Header("documentNumber") documentNumber: String): Response<Boolean?>

    @DELETE("medic")
    suspend fun deleteDoctor(@Header("documentNumber") documentNumber: String): Response<Boolean?>

}
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
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse?>

    @POST("/authentication-service/signup")
    suspend fun registerUser(@Body request: SignUpRequest): Response<Boolean?>

    @PUT("info")
    suspend fun alterData(@Body request: SignUpRequest): Response<Boolean?>

    @POST("medic")
    suspend fun registerDoctor(@Header("code") code: String,@Header("documentNumber") documentNumber: String): Response<Boolean?>

    @DELETE("medic")
    suspend fun deleteDoctor(@Header("documentNumber") documentNumber: String): Response<Boolean?>

    @GET("user-service/nutrition?")
    suspend fun getNutritionData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<Nutrition>

    @GET("user-service/health?")
    suspend fun getHealthData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<Nutrition>

    @GET("user-service/emotion?")
    suspend fun getEmotionData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<Nutrition>

    @GET("user-service/physical-activity?")
    suspend fun getPhysicalActivityData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<PhysicalActivity>
    @GET("nutrition-service/nutrition")
    suspend fun getDietMeals(
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<DietMealsResponse>


    @GET("physical-activity-service/physical-activity")
    suspend fun getPhysicalActivities(
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<PhysicalActivitiesResponse>

    @GET("/psychological-service/psychological")
    suspend fun getMedicines(
        @Header("accessToken") accessToken: String,
        @Header("sessionData") sessionData: String
    ): Response<GetMedicinesResponse>
}
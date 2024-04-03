package com.example.plumaFitApp.data.network

import com.example.plumaFitApp.data.models.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface RemoteApiService {
    @POST("/authentication-service/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse?>

    @POST("/authentication-service/signup")
    suspend fun registerUser(@Body request: SignUpRequest): Response<Boolean?>

    @GET("user-service/nutrition?")
    suspend fun getNutritionData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<Nutrition>

    @GET("user-service/health?")
    suspend fun getHealthData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<Nutrition>

    @GET("user-service/emotion?")
    suspend fun getEmotionData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<Nutrition>

    @GET("user-service/physical-activity?")
    suspend fun getPhysicalActivityData(
        @Query("from") from: String,
        @Query("to") to: String,
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<PhysicalActivity>


    @GET("nutrition-service/nutrition")
    suspend fun getDietMeals(
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<DietMealsResponse>


    @GET("physical-activity-service/physical-activity")
    suspend fun getPhysicalActivities(
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<PhysicalActivitiesResponse>

    @GET("/psychological-service/psychological")
    suspend fun getMedicines(
        @Header("Authorization") accessToken: String,
        @Header("SessionData") sessionData: String
    ): Response<GetMedicinesResponse>
}
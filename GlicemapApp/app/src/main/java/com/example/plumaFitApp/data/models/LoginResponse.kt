package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("sessionData")
    val sessionData: String
)
package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("username")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("role")
    val role: String
)
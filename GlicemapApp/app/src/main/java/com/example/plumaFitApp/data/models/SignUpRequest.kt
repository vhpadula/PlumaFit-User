package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("documentNumber")
    val documentNumber: String,
    @SerializedName("role")
    val role: String,
)
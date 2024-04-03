package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("documentNumber")
    val login: String,
    @SerializedName("password")
    val password: String,

)
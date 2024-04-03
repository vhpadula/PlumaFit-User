package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("documentNumber")
    val documentNumber: String,
    )
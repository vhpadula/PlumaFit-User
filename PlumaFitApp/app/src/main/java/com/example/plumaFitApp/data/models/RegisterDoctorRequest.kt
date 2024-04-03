package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class RegisterDoctorRequest (

    @SerializedName("documentNumber")
    val documentNumber: String,
    @SerializedName("code")
    val code: String
)
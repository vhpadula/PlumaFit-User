package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Doctor (
    @SerializedName("CRM")
    val documentNumber: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?
    )
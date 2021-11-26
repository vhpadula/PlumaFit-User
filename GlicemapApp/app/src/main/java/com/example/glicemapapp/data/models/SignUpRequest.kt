package com.example.glicemapapp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class SignUpRequest (
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("crmMedic")
    val crmMedic: String?,
    @SerializedName("documentNumber")
    val documentNumber: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("sugarMax")
    val sugarMax: Int,
    @SerializedName("sugarMin")
    val sugarMin: Int,
    @SerializedName("weight")
    val weight: Int
)
package com.example.glicemapapp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String

)
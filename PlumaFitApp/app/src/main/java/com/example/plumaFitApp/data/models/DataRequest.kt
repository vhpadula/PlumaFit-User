package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class DataRequest (
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("sessionData")
    val sessionData: String

)
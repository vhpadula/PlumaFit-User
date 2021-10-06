package com.example.contadormtg.models

import com.google.gson.annotations.SerializedName

data class MeasurementDetails(
    @SerializedName("sugarLevel")
    val sugarLevel: String,
    @SerializedName("insulin")
    val insulin: String,
    @SerializedName("situation")
    val situation: String,
    @SerializedName("observations")
    val observations: String
)


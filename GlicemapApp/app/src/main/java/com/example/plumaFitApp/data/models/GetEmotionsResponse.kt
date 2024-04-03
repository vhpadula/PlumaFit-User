package com.example.plumaFitApp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class GetEmotionsResponse (
    @SerializedName("emotions")
    val emotions: List<Emotion>
)
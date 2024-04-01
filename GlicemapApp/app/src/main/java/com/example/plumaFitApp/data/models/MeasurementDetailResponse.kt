package com.example.plumaFitApp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class MeasurementDetailResponse (
    @SerializedName("measures")
    val measures: List<MeasurementDetails>
)
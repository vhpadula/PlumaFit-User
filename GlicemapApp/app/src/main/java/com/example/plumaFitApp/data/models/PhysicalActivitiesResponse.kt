package com.example.plumaFitApp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class PhysicalActivitiesResponse (
    @SerializedName("exercises")
    val exercises: List<PhysicalActivity>
)
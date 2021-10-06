package com.example.glicemapapp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class SendMeasureRequest (
    @SerializedName("documentNumber")
    val documentNumber: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("measure")
    val measure: MeasurementDetails
)
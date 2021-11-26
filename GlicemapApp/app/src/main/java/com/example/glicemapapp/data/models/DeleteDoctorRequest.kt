package com.example.glicemapapp.data.models

import com.example.contadormtg.models.MeasurementDetails
import com.google.gson.annotations.SerializedName

data class DeleteDoctorRequest (

    @SerializedName("documentNumber")
    val documentNumber: String
)
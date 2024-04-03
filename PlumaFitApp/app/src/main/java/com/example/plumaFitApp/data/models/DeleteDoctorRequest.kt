package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class DeleteDoctorRequest (

    @SerializedName("documentNumber")
    val documentNumber: String
)
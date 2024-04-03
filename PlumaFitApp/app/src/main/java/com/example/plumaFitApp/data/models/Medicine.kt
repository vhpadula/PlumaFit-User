package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Medicine (
    @SerializedName("name")
    val name: String,
    @SerializedName("instructions")
    val instructions: String,
)

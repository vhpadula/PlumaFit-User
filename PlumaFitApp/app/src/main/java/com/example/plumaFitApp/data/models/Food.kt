package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Food (
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String,

    )
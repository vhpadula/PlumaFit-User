package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Meal (
    @SerializedName("name")
    val name: String,
    @SerializedName("foods")
    val foods: MutableList<Food>,
)

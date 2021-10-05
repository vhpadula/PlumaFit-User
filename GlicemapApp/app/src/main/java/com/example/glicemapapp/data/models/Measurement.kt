package com.example.contadormtg.models

import com.google.gson.annotations.SerializedName

data class Measurement(
    val date: String,
    val sugarLevel: String,
    val insulin: String,
    val situation: String,
    val observations: String
)


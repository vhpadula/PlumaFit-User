package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class PhysicalActivity (
    @SerializedName("name")
    val name: String,
    @SerializedName("reps_min")
    val reps_min: Int,
    @SerializedName("reps_max")
    val reps_max: Int,
    @SerializedName("series")
    val series: Int,
    @SerializedName("observation")
    val observation: String,
    )
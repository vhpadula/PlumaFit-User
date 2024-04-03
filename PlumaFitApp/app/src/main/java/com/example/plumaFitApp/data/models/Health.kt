package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Health (
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val id_user: String,
    @SerializedName("dh_registro")
    val dh_registro: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("oxigen")
    val oxigen: String,
    @SerializedName("blood_pressure")
    val blood_pressure: String,
    @SerializedName("observacao")
    val observacao: String,

    )
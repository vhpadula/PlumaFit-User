package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Nutrition (
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val id_user: String,
    @SerializedName("dh_registro")
    val dh_registro: String,
    @SerializedName("food")
    val food: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("observacao")
    val observacao: String,

    )
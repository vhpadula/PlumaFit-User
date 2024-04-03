package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class Emotion (
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val id_user: String,
    @SerializedName("dh_registro")
    val dh_registro: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("observacao")
    val observacao: String,

    )
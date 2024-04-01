package com.example.plumaFitApp.data.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("documentNumber")
    val documentNumber: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("sugarMin")
    val sugarMin: String,
    @SerializedName("sugarMax")
    val sugarMax: String,
    )
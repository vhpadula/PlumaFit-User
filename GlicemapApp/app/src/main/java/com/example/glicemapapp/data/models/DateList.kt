package com.example.glicemapapp.data.models

import com.google.gson.annotations.SerializedName

data class DateList(
    @SerializedName("dates")
    val dates: List<String>
)
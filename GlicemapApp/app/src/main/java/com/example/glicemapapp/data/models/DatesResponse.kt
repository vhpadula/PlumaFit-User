package com.example.glicemapapp.data.models

import com.google.gson.annotations.SerializedName

data class DatesResponse(
    @SerializedName("dates")
    val dates: List<String>
)
package com.example.plumaFitApp.data.models

import android.app.PendingIntent

data class Notification(
    val id: String,
    val name: String,
    val time: String,
    val dates: String,
    val intent: PendingIntent
)

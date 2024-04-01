package com.example.plumaFitApp.ui.main.notifications

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.plumaFitApp.data.models.Notification
import com.google.gson.Gson


class NotificationsViewModel : ViewModel() {

    lateinit var activity: Activity


    var items = mutableListOf<Notification>()

    fun setValues() {
        val preferences =
            activity.getSharedPreferences("notifications", Context.MODE_PRIVATE)
        val keys = preferences.all
        items = mutableListOf<Notification>()
        for (entry in keys.entries) {
            val gson = Gson()
            val json = entry.value.toString()
            val notification = gson.fromJson(json, Notification::class.java)
            if (notification.id != null) {
                if (items.isNullOrEmpty()) {
                    items.add(notification)
                } else {
                    for (item in items) {
                        if (item.id != notification.id) {
                            items.add(notification)
                        }
                    }
                }

            }


        }
    }
}
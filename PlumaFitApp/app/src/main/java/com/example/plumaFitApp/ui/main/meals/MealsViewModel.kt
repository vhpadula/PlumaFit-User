package com.example.plumaFitApp.ui.main.meals

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.plumaFitApp.data.Repository
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.DietMealsResponse
import com.example.plumaFitApp.data.models.Meal
import com.example.plumaFitApp.data.models.MeasurementDetailResponse
import com.example.plumaFitApp.data.models.Notification
import com.google.gson.Gson


class MealsViewModel : ViewModel() {
    private val repository = Repository


    lateinit var activity: Activity


    var items = mutableListOf<Meal>()

    fun setValues() {
//        val preferences =
//            activity.getSharedPreferences("notifications", Context.MODE_PRIVATE)
//        val keys = preferences.all
//        items = mutableListOf<Meal>()
//        for (entry in keys.entries) {
//            val gson = Gson()
//            val json = entry.value.toString()
//            val notification = gson.fromJson(json, Notification::class.java)
//            if (notification.id != null) {
//                if (items.isNullOrEmpty()) {
//                    items.add(notification)
//                } else {
//                    for (item in items) {
//                        if (item.id != notification.id) {
//                            items.add(notification)
//                        }
//                    }
//                }
//
//            }
//
//
//        }
    }
    fun loadDietMeals(): LiveData<Result<DietMealsResponse?>> = repository.getDietMeals()
}
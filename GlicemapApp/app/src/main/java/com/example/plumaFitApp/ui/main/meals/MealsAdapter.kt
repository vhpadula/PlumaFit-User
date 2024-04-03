package com.example.plumaFitApp.ui.main.meals

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contadormtg.models.MeasurementDetails
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.models.DietMealsResponse
import com.example.plumaFitApp.data.models.Meal
import com.example.plumaFitApp.data.models.MeasurementDetailResponse
import com.example.plumaFitApp.data.models.Notification
import com.example.plumaFitApp.databinding.MeasurementItemBinding
import com.example.plumaFitApp.databinding.NotificationItemBinding
import java.util.*


class MealsAdapter(val context: Context) :
    RecyclerView.Adapter<MealsAdapter.ItemViewHolder>() {


    var items = mutableListOf<Meal>()
        set(value) {
            val list = field.toMutableList()
            list.addAll(value)
            field = list
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Meal, position: Int) {
            binding.run {
                nameTv.text = item.name
                observationTv.text = item.foods.map { food ->
                    "${food.name}: ${food.quantity}"
                }.joinToString(separator = ", ")

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = NotificationItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position], position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun loadData(_items: DietMealsResponse) {
        val meals = _items.meals
        meals.forEach { meal ->
            items.add(meal)
        }
        notifyDataSetChanged()
    }

}
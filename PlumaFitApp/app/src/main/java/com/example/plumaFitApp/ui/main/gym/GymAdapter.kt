package com.example.plumaFitApp.ui.main.gym

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plumaFitApp.data.models.DietMealsResponse
import com.example.plumaFitApp.data.models.Meal
import com.example.plumaFitApp.data.models.PhysicalActivitiesResponse
import com.example.plumaFitApp.data.models.PhysicalActivity
import com.example.plumaFitApp.databinding.NotificationItemBinding


class GymAdapter(val context: Context) :
    RecyclerView.Adapter<GymAdapter.ItemViewHolder>() {


    var items = mutableListOf<PhysicalActivity>()
        set(value) {
            val list = field.toMutableList()
            list.addAll(value)
            field = list
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PhysicalActivity, position: Int) {
            binding.run {
                nameTv.text = item.name
                observationTv.text = "${item.reps_min} a ${item.reps_max} reps, ${item.series} series"
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

    fun loadData(_items: PhysicalActivitiesResponse) {
        val exercises = _items.exercises
        exercises.forEach {exercise  ->
            items.add(exercise)
        }
        notifyDataSetChanged()
    }

}
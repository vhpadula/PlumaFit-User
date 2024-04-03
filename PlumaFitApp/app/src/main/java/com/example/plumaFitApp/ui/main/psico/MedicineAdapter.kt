package com.example.plumaFitApp.ui.main.psico

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plumaFitApp.data.models.DietMealsResponse
import com.example.plumaFitApp.data.models.GetMedicinesResponse
import com.example.plumaFitApp.data.models.Meal
import com.example.plumaFitApp.data.models.Medicine
import com.example.plumaFitApp.databinding.NotificationItemBinding


class MedicineAdapter(val context: Context) :
    RecyclerView.Adapter<MedicineAdapter.ItemViewHolder>() {


    var items = mutableListOf<Medicine>()
        set(value) {
            val list = field.toMutableList()
            list.addAll(value)
            field = list
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Medicine, position: Int) {
            binding.run {
                nameTv.text = item.name
                observationTv.text = item.instructions

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

    fun loadData(_items: GetMedicinesResponse) {
        val medicines = _items.medicines
        medicines.forEach { medicine ->
            items.add(medicine)
        }
        notifyDataSetChanged()
    }

}
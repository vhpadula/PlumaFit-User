package com.example.plumaFitApp.ui.main.settings

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plumaFitApp.data.models.DietMealsResponse
import com.example.plumaFitApp.data.models.GetMedicinesResponse
import com.example.plumaFitApp.data.models.Meal
import com.example.plumaFitApp.data.models.Medicine
import com.example.plumaFitApp.data.models.User
import com.example.plumaFitApp.databinding.NotificationItemBinding
import com.example.plumaFitApp.databinding.ProfessionalItemBinding


class ProfessionalsAdapter(val context: Context) :
    RecyclerView.Adapter<ProfessionalsAdapter.ItemViewHolder>() {


    var items = mutableListOf<User>()
        set(value) {
            val list = field.toMutableList()
            list.addAll(value)
            field = list
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val binding: ProfessionalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User, position: Int) {
            binding.run {
                professionalNameTv.text = item.username

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ProfessionalItemBinding.inflate(
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

    fun loadData(_items: MutableList<User>) {
        _items.forEach { professional ->
            items.add(professional)
        }
        notifyDataSetChanged()
    }

}
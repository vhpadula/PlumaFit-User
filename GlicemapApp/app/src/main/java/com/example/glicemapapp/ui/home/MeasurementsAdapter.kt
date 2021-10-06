package com.example.glicemapapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.R
import com.example.glicemapapp.data.models.MeasurementDetailResponse
import com.example.glicemapapp.databinding.MeasurementItemBinding

class MeasurementsAdapter(val context: Context, val action: (position: Int) -> Unit) :
    RecyclerView.Adapter<MeasurementsAdapter.ItemViewHolder>() {


    var items = mutableListOf<MeasurementDetails>()
        set(value) {
            val list = field.toMutableList()
            list.addAll(value)
            field = list
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val binding: MeasurementItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MeasurementDetails, position: Int) {
            binding.run {
                situationTv.text = item.situation
                insulinTv.text = context.getString(R.string.measurement_insulin, item.insulin)
                sugarLevelNumberTv.text =
                    context.getString(R.string.measurement_sugar_level_rv, item.sugarLevel)
                root.setOnClickListener {
                    action.invoke(position)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = MeasurementItemBinding.inflate(
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

    fun loadData(_items: MeasurementDetailResponse) {
        val measures = _items.measures
        measures.forEach { measurement ->
            items.add(measurement)
        }
        notifyDataSetChanged()
    }
}
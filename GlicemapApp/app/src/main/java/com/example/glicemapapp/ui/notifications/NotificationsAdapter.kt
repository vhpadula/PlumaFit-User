package com.example.glicemapapp.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contadormtg.extracountersrv.ItemDragListener
import com.example.contadormtg.extracountersrv.ItemTouchHelperListener
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.R
import com.example.glicemapapp.data.models.MeasurementDetailResponse
import com.example.glicemapapp.data.models.Notification
import com.example.glicemapapp.databinding.MeasurementItemBinding
import com.example.glicemapapp.databinding.NotificationItemBinding
import java.util.*

class NotificationsAdapter(val context: Context, private val itemDragListener: ItemDragListener) :
    RecyclerView.Adapter<NotificationsAdapter.ItemViewHolder>() ,ItemTouchHelperListener{


    var items = mutableListOf<Notification>()
        set(value) {
            val list = field.toMutableList()
            list.addAll(value)
            field = list
            notifyDataSetChanged()
        }

    inner class ItemViewHolder(private val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Notification) {
            binding.run {
               nameTv.text = item.name
              timeTv.text = item.time
               datesTv.text = item.dates
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
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onItemMove(
        recyclerView: RecyclerView,
        fromPosition: Int,
        toPosition: Int
    ): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)

            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

}
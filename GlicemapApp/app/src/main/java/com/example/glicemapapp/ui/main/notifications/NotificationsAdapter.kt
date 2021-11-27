package com.example.glicemapapp.ui.main.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contadormtg.extracountersrv.ItemDragListener
import com.example.contadormtg.extracountersrv.ItemTouchHelperListener
import com.example.glicemapapp.data.models.Notification
import com.example.glicemapapp.databinding.NotificationItemBinding
import java.util.*
import android.preference.PreferenceManager

import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.glicemapapp.AlarmReceiver


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

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("id", items[position].id)
        val pendingIntent = PendingIntent.getBroadcast(context, items[position].id.toInt(), intent, 0)
        alarmManager.cancel(pendingIntent)
        val preferences =
            context.getSharedPreferences("notifications", Context.MODE_PRIVATE)
        if(preferences.getString(items[position].id, null) != null){
            preferences.edit().remove(items[position].id).apply()
        }

        items.removeAt(position)

        notifyItemRemoved(position)
    }

}
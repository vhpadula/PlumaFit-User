package com.example.glicemapapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.glicemapapp.ui.main.MainActivity
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    lateinit var preferences: SharedPreferences

    override fun onReceive(context: Context, intent: Intent?) {
        val i = Intent(context, MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)
        val builder = NotificationCompat.Builder(context, "Glicemap")
        builder.run {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle("Glicemap")
            setContentText("Hora de medir sua glicemia!")
            setAutoCancel(true)
            setDefaults(NotificationCompat.DEFAULT_ALL)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setContentIntent(pendingIntent)
            val preferences =
                context.getSharedPreferences("notifications", Context.MODE_PRIVATE)
            val recurring = intent!!.extras!!.getBoolean("recurring")
            val id = intent?.extras?.getString("id")
            if (!recurring){
                if(preferences.getString(id, null) != null){
                    preferences.edit().remove(id).apply()
                }
            } else {
                val currentDate = Calendar.getInstance()
                currentDate[Calendar.DAY_OF_MONTH] = currentDate.get(Calendar.DAY_OF_MONTH) + 7
                val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(context, AlarmReceiver::class.java)
                intent.putExtra("id", id)
                intent.putExtra("recurring", true)
                val pendingIntent = PendingIntent.getBroadcast(context, id!!.toInt(), intent, 0)
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, currentDate.timeInMillis, pendingIntent)
            }


        }
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(123, builder.build())
    }
}
package com.example.glicemapapp.ui.main.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.glicemapapp.AlarmReceiver
import com.example.glicemapapp.data.models.Notification
import com.example.glicemapapp.databinding.FragmentNewNotificationsBinding
import com.example.glicemapapp.ui.base.ToolbarFragment
import com.example.glicemapapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


class NewNotificationsFragment : ToolbarFragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNewNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val cal = Calendar.getInstance()


    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(requireActivity()).get(NotificationsViewModel::class.java)

        _binding = FragmentNewNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setTimePicker()
        setListeners()
        createNotificationChannel()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTimePicker() {
        binding.timeEt.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.timeEt.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                requireContext(),
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun setListeners() {
        binding.saveButton.setOnClickListener {
            if (binding.nameEt.text.isNullOrEmpty() || binding.timeEt.text.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    "O nome e o horário não podem ser vazios!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                var dates = ""
                if (binding.dom.isChecked || binding.seg.isChecked || binding.ter.isChecked || binding.qua.isChecked || binding.qui.isChecked || binding.sex.isChecked || binding.sab.isChecked) {

                    if (binding.dom.isChecked) {
                        dates += " Dom "
                    }

                    if (binding.seg.isChecked) {
                        dates += " Seg "
                    }

                    if (binding.ter.isChecked) {
                        dates += " Ter "
                    }

                    if (binding.qua.isChecked) {
                        dates += " Qua "
                    }

                    if (binding.qui.isChecked) {
                        dates += " Qui "
                    }

                    if (binding.sex.isChecked) {
                        dates += " Sex "
                    }

                    if (binding.sab.isChecked) {
                        dates += " Sab "
                    }
                    if (binding.dom.isChecked && binding.seg.isChecked && binding.ter.isChecked && binding.qua.isChecked && binding.qui.isChecked && binding.sex.isChecked && binding.sab.isChecked) {
                        dates = "Todos os dias"
                    }
                } else {
                    dates = "Só uma vez"
                }


                val preferences =
                    requireContext().getSharedPreferences("notifications", Context.MODE_PRIVATE)
                val keys = preferences.all
                var biggestId = 0
                for (entry in keys.entries){
                    val gson = Gson()
                    val json = entry.value.toString()
                    val notification = gson.fromJson(json, Notification::class.java)
                    if(notification.id!=null){
                        if (notification.id.toInt()>= biggestId){
                            biggestId= notification.id.toInt()
                        }
                    }

                }
                val notificationId = (biggestId+1).toString()
                setAlarm(preferences, notificationId)
                val notification = Notification(
                    notificationId,
                    binding.nameEt.text.toString(),
                    binding.timeEt.text.toString(),
                    dates,
                    pendingIntent
                )
                notificationsViewModel.items.add(
                    notification
                )
                val editor = preferences.edit()
                val gson = Gson()
                val json = gson.toJson(notification)
                editor.putString(notification.id, json);
                editor.apply()

                findNavController().navigate(com.example.glicemapapp.ui.main.notifications.NewNotificationsFragmentDirections.toNotification())
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "GlicemapChannel"
            val description = "Channel for alarm"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("Glicemap", name, importance)
            channel.description = description

            val notificationManager: NotificationManager =
                requireActivity().getSystemService(
                    NotificationManager::class.java
                ) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setAlarm(preferences: SharedPreferences, id: String) {
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra("id", id)
        if (binding.dom.isChecked || binding.seg.isChecked || binding.ter.isChecked || binding.qua.isChecked || binding.qui.isChecked || binding.sex.isChecked || binding.sab.isChecked){
            intent.putExtra("recurring", true)
            pendingIntent = PendingIntent.getBroadcast(context, id.toInt(), intent, 0)

            val currentDate = Calendar.getInstance()
            if (binding.dom.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.SUNDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

            if (binding.seg.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.MONDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

            if (binding.ter.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.TUESDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

            if (binding.qua.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.WEDNESDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

            if (binding.qui.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.THURSDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

            if (binding.sex.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.FRIDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

            if (binding.sab.isChecked) {
                while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.SATURDAY) {
                    currentDate.add(Calendar.DAY_OF_MONTH, 1)
                }

                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0

                alarmManager.setRepeating(
                    AlarmManager.RTC,
                    currentDate.timeInMillis,
                    AlarmManager.INTERVAL_DAY * 7,
                    pendingIntent
                )
            }

        } else{
            intent.putExtra("recurring", false)
            pendingIntent = PendingIntent.getBroadcast(context, id.toInt(), intent, 0)
            val currentDate = Calendar.getInstance()
            if(cal.timeInMillis > currentDate.timeInMillis){
                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, currentDate.timeInMillis, pendingIntent)
            } else {
                currentDate[Calendar.DAY_OF_MONTH] = cal.get(Calendar.DAY_OF_MONTH) + 1
                currentDate[Calendar.HOUR_OF_DAY] = cal.get(Calendar.HOUR_OF_DAY)
                currentDate[Calendar.MINUTE] = cal.get(Calendar.MINUTE)
                currentDate[Calendar.SECOND] = 0
                currentDate[Calendar.MILLISECOND] = 0
            }
        }


    }

}
package com.example.glicemapapp.ui.main.notifications

import android.app.*
import android.content.Context
import android.content.Intent
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
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class NewNotificationsFragment : ToolbarFragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNewNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setTimePicker() {
        binding.timeEt.setOnClickListener {
            val cal = Calendar.getInstance()
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
            if (binding.nameEt.text.isNullOrEmpty() && binding.timeEt.text.isNullOrEmpty()) {
                Snackbar.make(
                    binding.root,
                    "O nome e o horário não podem ser vazios!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                var dates = ""
                if(binding.dom.isChecked || binding.seg.isChecked || binding.ter.isChecked || binding.qua.isChecked || binding.qui.isChecked || binding.sex.isChecked || binding.sab.isChecked) {

                    if (binding.dom.isChecked) {
                        dates+=" Dom "
                    }

                    if (binding.seg.isChecked) {
                        dates+=" Seg "
                    }

                    if (binding.ter.isChecked) {
                        dates+=" Ter "
                    }

                    if (binding.qua.isChecked) {
                        dates+=" Qua "
                    }

                    if (binding.qui.isChecked) {
                        dates+=" Qui "
                    }

                    if (binding.sex.isChecked) {
                        dates+=" Sex "
                    }

                    if (binding.sab.isChecked) {
                        dates+= " Sab "
                    }
                    if (binding.dom.isChecked && binding.seg.isChecked && binding.ter.isChecked && binding.qua.isChecked && binding.qui.isChecked && binding.sex.isChecked && binding.sab.isChecked) {
                        dates = "Todos os dias"
                    }
                } else{
                    dates = "Só uma vez"
                }
                notificationsViewModel.items.add(Notification(binding.nameEt.text.toString(), binding.timeEt.text.toString(),dates))
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
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setAlarmMonday(hour: Int, minute: Int){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)
        val currentDate = Calendar.getInstance()

        while (currentDate[Calendar.DAY_OF_WEEK] !== Calendar.MONDAY) {
            currentDate.add(Calendar.DATE, 1)
        }
        currentDate[Calendar.HOUR_OF_DAY] = hour
        currentDate[Calendar.MINUTE] = minute
        currentDate[Calendar.SECOND] = 0
        currentDate[Calendar.MILLISECOND] = 0

        intent.putExtra("extra info", "if needed")
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            currentDate.timeInMillis,
            AlarmManager.INTERVAL_DAY * 7,
            pendingIntent
        )
    }

}
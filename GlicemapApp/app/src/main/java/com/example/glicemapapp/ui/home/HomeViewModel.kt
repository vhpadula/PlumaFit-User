package com.example.glicemapapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.Measurement
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import java.text.SimpleDateFormat
import java.util.*


class HomeViewModel : ViewModel() {
    private val repository = Repository
    var measurements = listOf<Measurement?>()


//     fun loadData() : LiveData<Result<Measurement?>> = repository.getMeasurements("123")
     fun loadData() : LiveData<Result<List<Measurement?>?>> = repository.getMeasurements("123")

    fun setCalendarData(measurements: List<Measurement?>): MutableList<EventDay>{
        this.measurements = measurements
        val locale = Locale ("pt", "BR")
        val sdf = SimpleDateFormat("dd-MM-yyyy", locale)
        val events: MutableList<EventDay> = ArrayList()
        measurements.forEach { measurement ->
            val calendar = Calendar.getInstance()
            calendar.time = sdf.parse(measurement!!.date)
            events.add(EventDay(calendar, R.drawable.ic_done_white_24dp))
        }
        return events
    }


}
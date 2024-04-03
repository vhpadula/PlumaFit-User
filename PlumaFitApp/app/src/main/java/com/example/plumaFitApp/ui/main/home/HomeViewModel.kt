package com.example.plumaFitApp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.MeasurementDetails
import com.example.plumaFitApp.R
import com.example.plumaFitApp.data.Repository
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.DatesResponse
import com.example.plumaFitApp.data.models.MeasurementDetailResponse
import com.example.plumaFitApp.data.models.User
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class HomeViewModel : ViewModel() {
    private val repository = Repository
    private var currentDate = LocalDate.now()
    lateinit var user: User

    fun loadDates(addMonth: Int) {
        var year = currentDate.year
        var month = currentDate.monthValue
        if (currentDate.monthValue == 12 && addMonth == 1) {
            year += 1
            month = 1
        } else if (currentDate.monthValue == 1 && addMonth == -1) {
            year -= 1
            month = 12
        } else {
            month += addMonth
        }
        currentDate = LocalDate.of(year, month, 1)
        val date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return
    }






    fun setCalendarData(_dates: DatesResponse): MutableList<EventDay> {
        val dates = _dates.dates
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val events: MutableList<EventDay> = ArrayList()
        dates.forEach { date ->
            val calendar = Calendar.getInstance()
            calendar.time = sdf.parse(date)
            events.add(EventDay(calendar, R.drawable.ic_done_white_24dp))
        }
        return events
    }




}
package com.example.glicemapapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.DatesResponse
import com.example.glicemapapp.data.models.MeasurementDetailResponse
import com.example.glicemapapp.data.models.User
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class HomeViewModel : ViewModel() {
    private val repository = Repository
    private var currentDate = LocalDate.now()
    lateinit var user: User

    fun loadDates(addMonth: Int): LiveData<Result<DatesResponse?>> {
        val month = currentDate.monthValue + addMonth
        currentDate = LocalDate.of(2021, month, 1)
        val date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return repository.getMeasurementDates("22949837859", date)
    }

    fun loadDayMeasurementDetails(date: String): LiveData<Result<MeasurementDetailResponse?>> =
        repository.getMeasurementDetails("22949837859", date)


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

    fun registerMeasurement(
        sugarLevel: String,
        insulin: String,
        situation: String,
        observations: String?
    ): LiveData<Result<Boolean?>> {
        val _date = LocalDate.now()
        val date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return repository.sendMeasurement(
            "22949837859",
            date,
            MeasurementDetails(sugarLevel, insulin, situation, observations)
        )
    }


}
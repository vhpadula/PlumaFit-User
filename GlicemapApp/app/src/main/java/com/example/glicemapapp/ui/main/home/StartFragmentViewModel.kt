package com.example.glicemapapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.DatesResponse
import com.example.glicemapapp.data.models.GetUserResponse
import com.example.glicemapapp.data.models.MeasurementDetailResponse
import com.example.glicemapapp.data.models.User
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class StartFragmentViewModel : ViewModel() {
    private val repository = Repository
    fun getUser(cpf: String): LiveData<Result<GetUserResponse?>> = repository.getUser(cpf)





}
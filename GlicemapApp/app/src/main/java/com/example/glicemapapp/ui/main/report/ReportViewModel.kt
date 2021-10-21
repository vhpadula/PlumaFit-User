package com.example.glicemapapp.ui.main.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.models.User

class ReportViewModel : ViewModel() {
    private val repository = Repository
    lateinit var user: User
    var minDate = ""
    var maxDate= ""
    fun getPDF() = repository.getPDF(user.documentNumber,minDate,maxDate)
}
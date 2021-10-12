package com.example.glicemapapp.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glicemapapp.data.Repository

class ReportViewModel : ViewModel() {
    private val repository = Repository
    fun getPDF(cpf: String, minDate: String, maxDate: String) = repository.getPDF(cpf,minDate,maxDate)
}
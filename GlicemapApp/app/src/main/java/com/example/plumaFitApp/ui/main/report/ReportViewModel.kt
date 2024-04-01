package com.example.plumaFitApp.ui.main.report

import androidx.lifecycle.ViewModel
import com.example.plumaFitApp.data.Repository
import com.example.plumaFitApp.data.models.User

class ReportViewModel : ViewModel() {
    private val repository = Repository
    lateinit var user: User
    var minDate = ""
    var maxDate= ""
    fun getPDF() = repository.getPDF(user.documentNumber,minDate,maxDate)
}
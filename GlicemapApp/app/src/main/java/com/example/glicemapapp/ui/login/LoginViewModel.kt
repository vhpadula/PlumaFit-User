package com.example.glicemapapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.R
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class LoginViewModel : ViewModel() {
    private val repository = Repository
    var name = ""
    var lastName=""
    var documentNumber = ""
    var email = ""
    var password = ""
    var birthdate= ""
    var height = 0
    var weight = 0
    var sugarMin = 0
    var sugarmax = 0

    fun loginUser(
        login: String,
        password: String
    ): LiveData<Result<String?>> {

        return repository.loginUser(
            LoginRequest(login,password)
        )
    }

    fun registerUser(

    ): LiveData<Result<Boolean?>> {

        return repository.registerUser(SignUpRequest(birthdate, null, documentNumber, email, height, lastName, name, password, sugarmax, sugarMin, weight))
    }


    fun getUser(cpf: String): LiveData<Result<GetUserResponse?>> = repository.getUser(cpf)


}
package com.example.plumaFitApp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.plumaFitApp.data.Repository
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.*


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
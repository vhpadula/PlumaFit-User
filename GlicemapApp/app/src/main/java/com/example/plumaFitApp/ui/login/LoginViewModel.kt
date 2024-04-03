package com.example.plumaFitApp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.plumaFitApp.data.Repository
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.*


class LoginViewModel : ViewModel() {
    private val repository = Repository
    var name = ""
    var documentNumber = ""
    var password = ""

    fun loginUser(
    ): LiveData<Result<LoginResponse?>> {

        return repository.loginUser(
            LoginRequest(documentNumber,password, role="USER")
        )
    }

    fun registerUser(

    ): LiveData<Result<Boolean?>> {

        return repository.registerUser(SignUpRequest(username = documentNumber, password = password, email = name, documentNumber = documentNumber, role = "USER"))
    }


    fun getUser(cpf: String): LiveData<Result<GetUserResponse?>> = repository.getUser(cpf)


}
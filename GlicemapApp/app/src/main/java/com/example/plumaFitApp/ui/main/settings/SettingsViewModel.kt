package com.example.plumaFitApp.ui.main.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.plumaFitApp.data.Repository
import com.example.plumaFitApp.data.Result
import com.example.plumaFitApp.data.models.DeleteDoctorRequest
import com.example.plumaFitApp.data.models.RegisterDoctorRequest
import com.example.plumaFitApp.data.models.SignUpRequest
import com.example.plumaFitApp.data.models.User

class SettingsViewModel : ViewModel() {
    lateinit var user: User
    private val repository = Repository

    fun alterData(request: SignUpRequest

    ): LiveData<Result<Boolean?>> {

        return repository.alterData(request)
    }

    fun registerDoctor(request: RegisterDoctorRequest

    ): LiveData<Result<Boolean?>> {

        return repository.registerDoctor(request)
    }

    fun deleteDoctor(

    ): LiveData<Result<Boolean?>> {

        return repository.deleteDoctor(DeleteDoctorRequest(user.documentNumber))
    }
}
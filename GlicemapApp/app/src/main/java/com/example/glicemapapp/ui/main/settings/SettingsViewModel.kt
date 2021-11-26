package com.example.glicemapapp.ui.main.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glicemapapp.data.Repository
import com.example.glicemapapp.data.Result
import com.example.glicemapapp.data.models.DeleteDoctorRequest
import com.example.glicemapapp.data.models.RegisterDoctorRequest
import com.example.glicemapapp.data.models.SignUpRequest
import com.example.glicemapapp.data.models.User

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
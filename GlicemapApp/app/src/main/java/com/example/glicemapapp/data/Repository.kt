package com.example.glicemapapp.data

import androidx.lifecycle.liveData
import com.example.contadormtg.networking.RetrofitBuilder

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T?>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object Repository  {

    private val service by lazy { RetrofitBuilder.buildApiService()}

    fun getMeasurementDates(cpf: String, date: String) = liveData {
        try {
            val response = service.getMeasurementDates(cpf,date)
            if(response.isSuccessful){
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Falha ao buscar o endereco")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getMeasurementDetails(cpf: String, date: String) = liveData {
        try {
            val response = service.getMeasurementDetails(cpf,date)
            if(response.isSuccessful){
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Falha ao buscar o endereco")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }
}
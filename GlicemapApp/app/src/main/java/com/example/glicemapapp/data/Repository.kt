package com.example.glicemapapp.data

import androidx.lifecycle.liveData
import com.example.contadormtg.models.MeasurementDetails
import com.example.contadormtg.networking.RetrofitBuilder
import com.example.glicemapapp.data.models.SendMeasureRequest

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
                emit(Result.Error(exception = Exception("Houve uma falha ao buscar as medidas, tente novamente mais tarde")))
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
                emit(Result.Error(exception = Exception("Houve uma falha ao buscar as medidas, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun sendMeasurement(cpf: String, date: String, measure: MeasurementDetails) = liveData {
        try {
            val response = service.sendMeasurement(SendMeasureRequest( cpf,date, measure))
            if(response.isSuccessful){
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

}
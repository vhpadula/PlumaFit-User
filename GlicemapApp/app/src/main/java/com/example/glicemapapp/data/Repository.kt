package com.example.glicemapapp.data

import androidx.lifecycle.liveData
import com.example.contadormtg.models.MeasurementDetails
import com.example.glicemapapp.data.models.*
import com.example.glicemapapp.data.network.RetrofitBuilder

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T?>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object Repository {

    lateinit var user: User
    lateinit var doctor: Doctor

    private val service by lazy { RetrofitBuilder.buildApiService() }

    fun getMeasurementDates(cpf: String, date: String) = liveData {
        try {
            val response = service.getMeasurementDates(cpf, date)
            if (response.isSuccessful) {
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
            val response = service.getMeasurementDetails(cpf, date)
            if (response.isSuccessful) {
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
            val response = service.sendMeasurement(SendMeasureRequest(cpf, date, measure))
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getUser(cpf: String) = liveData {
        try {
            val response = service.getUser(cpf)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao buscar as medidas, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }


    fun getPDF(cpf: String, minDate: String, maxDate: String) = liveData {
        try {
            val response = service.getPDF(cpf, minDate, maxDate)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception(response.toString())))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun loginUser(request: LoginRequest) = liveData {
        try {
            val response = service.loginUser(request)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }


    fun registerUser(request: SignUpRequest) = liveData {
        try {
            val response = service.registerUser(request)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }


    fun alterData(request: SignUpRequest) = liveData {
        try {
            val response = service.alterData(request)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun registerDoctor(request: RegisterDoctorRequest) = liveData {
        try {
            val response = service.registerDoctor(request)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun deleteDoctor(request: DeleteDoctorRequest) = liveData {
        try {
            val response = service.deleteDoctor(request)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

}
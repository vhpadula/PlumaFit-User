package com.example.plumaFitApp.data

import androidx.lifecycle.liveData
import com.example.contadormtg.models.MeasurementDetails
import com.example.plumaFitApp.data.models.*
import com.example.plumaFitApp.data.network.RetrofitBuilder

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T?>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object Repository {

    lateinit var user: User
    lateinit var doctor: Doctor

    lateinit var accessToken: String;
    lateinit var sessionData: String;

    private val service by lazy { RetrofitBuilder.buildApiService() }











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



    fun getEmotion(request: DataRequest) = liveData {
        try {
            val response = service.getEmotionData(request.from, request.to, request.accessToken, request.sessionData)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getHealth(request: DataRequest) = liveData {
        try {
            val response = service.getHealthData(request.from, request.to, request.accessToken, request.sessionData)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getNutrition(request: DataRequest) = liveData {
        try {
            val response = service.getNutritionData(request.from, request.to, request.accessToken, request.sessionData)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getExercise(request: DataRequest) = liveData {
        try {
            val response = service.getPhysicalActivityData(request.from, request.to, request.accessToken, request.sessionData)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getDietMeals() = liveData {
        try {
            val response = service.getDietMeals(accessToken, sessionData)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getPhysicalActivities() = liveData {
        try {
            val response = service.getPhysicalActivities(accessToken, sessionData)
            if (response.isSuccessful) {
                emit(Result.Success(data = response.body()))
            } else {
                emit(Result.Error(exception = Exception("Houve uma falha ao enviar a medida, tente novamente mais tarde")))
            }
        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getMedicines() = liveData {
        try {
            val response = service.getMedicines(accessToken, sessionData)
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
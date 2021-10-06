package com.example.contadormtg.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun provideRetrofit() : Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return Retrofit.Builder().baseUrl("https://glicemap-backend.azurewebsites.net/app/")
            .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build())
            .build()
    }

    fun buildApiService(): RemoteApiService = provideRetrofit().create(RemoteApiService::class.java)
}
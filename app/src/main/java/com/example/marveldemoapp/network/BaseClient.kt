package com.example.marveldemoapp.network

import com.example.marveldemoapp.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseClient {

    private val apiService: Api by lazy {
        retrofit().create(Api::class.java)
    }

    private fun retrofit(): Retrofit {
        val gson = GsonBuilder().setDateFormat("hh:mm:ss dd/MM/yyyy").create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun provideMarvelApi(): Api {
        return apiService
    }
}
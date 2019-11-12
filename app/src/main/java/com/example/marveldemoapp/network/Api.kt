package com.example.marveldemoapp.network

import com.example.marveldemoapp.models.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: String?
    ): Call<MarvelResponse>

}
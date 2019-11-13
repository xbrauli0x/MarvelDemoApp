package com.example.marveldemoapp.network

import com.example.marveldemoapp.models.Comic
import com.example.marveldemoapp.models.MarvelItem
import com.example.marveldemoapp.models.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: String?
    ): Call<MarvelResponse<MarvelItem>>

    @GET("v1/public/characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: String,
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: String?
    ): Call<MarvelResponse<Comic>>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterInfo(
        @Path("characterId") characterId: String,
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?
    ): Call<MarvelResponse<MarvelItem>>

}
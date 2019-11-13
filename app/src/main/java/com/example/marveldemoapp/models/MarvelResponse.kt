package com.example.marveldemoapp.models

import com.google.gson.annotations.SerializedName

data class MarvelResponse<T>(
    @SerializedName("code") var statusCode: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("data") var data: Data<T>
)

data class Data<T>(@SerializedName("results") val results: List<T>?)
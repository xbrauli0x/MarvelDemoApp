package com.example.marveldemoapp.models

import com.google.gson.annotations.SerializedName

class MarvelItem {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
        get() {
            return if (field.equals("")) "No description" else field
        }
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null
}

data class Thumbnail(
    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null
)

data class Data(@SerializedName("results") val results: List<MarvelItem>?)

data class MarvelResponse(
    @SerializedName("code") var statusCode: String? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("data") var data: Data
)
package com.example.marveldemoapp.models

import com.example.marveldemoapp.utils.Utils
import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path") var path: String? = null,
    @SerializedName("extension") var extension: String? = null
) {
    val url:String?
        get() {
            return Utils.getUriImage(path, extension)
        }
}
package com.example.marveldemoapp.models

import com.google.gson.annotations.SerializedName

class Comic {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null
        get() {
            return if (field.equals("")) "No description" else field
        }

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null

}

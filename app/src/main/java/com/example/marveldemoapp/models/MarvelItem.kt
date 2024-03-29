package com.example.marveldemoapp.models

import com.google.gson.annotations.SerializedName

class MarvelItem {

    companion object {
        val TAG: String = MarvelItem::class.java.simpleName
        val NAME:String = "NAME"
    }

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
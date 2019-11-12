package com.example.marveldemoapp.utils

import android.content.Context
import com.example.marveldemoapp.R
import org.koin.core.KoinComponent
import org.koin.core.inject

class MarvelRequestParamsGenerator : KoinComponent {

    var timestamp: Long? = null
    var apiKey: String? = null
    var hash: String? = null
    var limit: String? = null

    fun setUpRequestParams() {
        timestamp = System.currentTimeMillis()
        apiKey = "13ef0e1a3cda71f28e1c7278b5d1abd2"
        val privateKey = "bcab2ae8ff7d4b24f24ba0750caf3c3139b78c77"
        val hashCandidate: String = timestamp.toString() + privateKey + apiKey
        hash = hashCandidate.toMD5()
        limit = getLimitFromResources()
    }

    private fun getLimitFromResources(): String {
        val ctx: Context by inject()
        return ctx.resources.getInteger(R.integer.max_items_allowed).toString()
    }
}
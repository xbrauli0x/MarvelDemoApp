package com.example.marveldemoapp.utils

class MarvelRequestGenerator private constructor(){

    companion object {
        fun createParams(): MarvelRequestGenerator {
            val generator = MarvelRequestGenerator()
            generator.setUpRequestParams()
            return generator
        }
    }

    var timestamp: Long? = null
    var apiKey: String? = null
    var hash: String? = null

    private fun setUpRequestParams() {
        timestamp = System.currentTimeMillis()
        apiKey = "13ef0e1a3cda71f28e1c7278b5d1abd2"
        val privateKey = "bcab2ae8ff7d4b24f24ba0750caf3c3139b78c77"
        val hashCandidate: String = timestamp.toString() + privateKey + apiKey
        hash = hashCandidate.toMD5()
    }

}
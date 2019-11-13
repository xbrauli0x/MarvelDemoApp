package com.example.marveldemoapp.utils

import com.example.marveldemoapp.models.Data
import com.example.marveldemoapp.models.MarvelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarvelResponseHandler<T>(
    private val call: Call<MarvelResponse<T>>,
    val callback: (List<T>?) -> Unit,
    val errorCallback: (String?) -> Unit
) {

    fun makeRequest() {
        call.enqueue(object : Callback<MarvelResponse<T>> {
            override fun onFailure(call: Call<MarvelResponse<T>>, t: Throwable) {
                errorCallback(t.message)
            }

            override fun onResponse(
                call: Call<MarvelResponse<T>>,
                response: Response<MarvelResponse<T>>
            ) {
                if (response.isSuccessful) {
                    getMarvelCharactersFromResponse(response)
                } else {
                    errorCallback(response.message())
                }
            }

        })
    }

    private fun getMarvelCharactersFromResponse(response: Response<MarvelResponse<T>>) {
        val marvelResponse: MarvelResponse<T>? = response.body()
        val data: Data<T>? = marvelResponse?.data
        callback(data?.results)
    }
}
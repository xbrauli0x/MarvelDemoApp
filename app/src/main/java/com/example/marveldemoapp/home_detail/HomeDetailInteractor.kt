package com.example.marveldemoapp.home_detail

import com.example.marveldemoapp.models.Data
import com.example.marveldemoapp.models.MarvelItem
import com.example.marveldemoapp.models.MarvelResponse
import com.example.marveldemoapp.network.BaseClient
import com.example.marveldemoapp.utils.MarvelRequestParamsGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeDetailInteractor(var responseListener: HomeDetailContract.Interactor.ResponseListener?) :
    HomeDetailContract.Interactor {

    override fun fetchMarvelData(limit: String?) {
        val generator = MarvelRequestParamsGenerator(limit)
        generator.setUpRequestParams()
        val call = BaseClient.provideMarvelApi()
            .getCharacters(generator.timestamp, generator.apiKey, generator.hash, generator.limit)
        call.enqueue(object : Callback<MarvelResponse> {
            override fun onFailure(call: Call<MarvelResponse>, t: Throwable) {
                responseListener?.onErrorFetchingData(t.message)
            }

            override fun onResponse(
                call: Call<MarvelResponse>,
                response: Response<MarvelResponse>
            ) {
                if (response.isSuccessful) {
                    getMarvelCharactersFromResponse(response)
                } else {
                    responseListener?.onErrorFetchingData(response.message())
                }
            }
        })
    }

    private fun getMarvelCharactersFromResponse(response: Response<MarvelResponse>) {
        val marvelResponse: MarvelResponse? = response.body()
        val data: Data? = marvelResponse?.data
        responseListener?.onMarvelDataFetched(data?.results)
    }

    override fun fetchMarvelCharacterInfo(item: MarvelItem) {

    }

}
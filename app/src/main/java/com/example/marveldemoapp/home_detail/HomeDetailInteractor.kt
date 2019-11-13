package com.example.marveldemoapp.home_detail

import android.content.Context
import com.example.marveldemoapp.R
import com.example.marveldemoapp.network.BaseClient
import com.example.marveldemoapp.utils.MarvelRequestGenerator
import com.example.marveldemoapp.utils.MarvelResponseHandler
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeDetailInteractor(var responseListener: HomeDetailContract.Interactor.ResponseListener?) :
    HomeDetailContract.Interactor, KoinComponent {

    override fun fetchMarvelData() {
        val limit = getMaxItemsAllowed()
        val generator = MarvelRequestGenerator.createParams()
        val call = BaseClient.provideMarvelApi()
            .getCharacters(generator.timestamp, generator.apiKey, generator.hash, limit)
        val responseHandler = MarvelResponseHandler(call,
            {
                responseListener?.onMarvelDataFetched(it)
            },
            {
                responseListener?.onErrorFetchingData(it)
            })
        responseHandler.makeRequest()
    }


    private fun getMaxItemsAllowed(): String {
        val ctx: Context by inject()
        return ctx.resources.getInteger(R.integer.max_items_allowed).toString()
    }
}
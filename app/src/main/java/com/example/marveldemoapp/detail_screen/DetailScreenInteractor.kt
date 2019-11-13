package com.example.marveldemoapp.detail_screen

import android.content.Context
import com.example.marveldemoapp.R
import com.example.marveldemoapp.network.BaseClient
import com.example.marveldemoapp.utils.MarvelRequestGenerator
import com.example.marveldemoapp.utils.MarvelResponseHandler
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailScreenInteractor(var responseListener: DetailScreenContract.Interactor.ResponseListener?) :
    DetailScreenContract.Interactor, KoinComponent {

    override fun fetchComics(characterId: String) {
        val limit = getComicsLimit()
        val generator = MarvelRequestGenerator.createParams()
        val call = BaseClient.provideMarvelApi()
            .getComics(characterId, generator.timestamp, generator.apiKey, generator.hash, limit)
        val responseHandler = MarvelResponseHandler(call,
            {
                responseListener?.onComicsFetched(it)
            }, {
                responseListener?.onErrorFetchingData(it)
            })
        responseHandler.makeRequest()
    }

    override fun fetchCharacterInfo(characterId: String) {
        val generator = MarvelRequestGenerator.createParams()
        val call = BaseClient.provideMarvelApi()
            .getCharacterInfo(characterId, generator.timestamp, generator.apiKey, generator.hash)
        val responseHandler = MarvelResponseHandler(call,
            {
                responseListener?.onCharacterInfoFetched(it)
            }, {
                responseListener?.onErrorFetchingData(it)
            })
        responseHandler.makeRequest()
    }

    private fun getComicsLimit(): String {
        val ctx: Context by inject()
        return ctx.resources.getInteger(R.integer.max_comic_items_allowed).toString()
    }
}
package com.example.marveldemoapp.detail_screen

import com.example.marveldemoapp.base.BaseContract
import com.example.marveldemoapp.models.Comic
import com.example.marveldemoapp.models.MarvelItem

interface DetailScreenContract {

    interface View : BaseContract.View {
        fun renderComics(data: List<Comic>?)
        fun renderCharacterInfo(marvelItem: MarvelItem?)
    }

    interface Presenter : BaseContract.Presenter {
        fun getCharacterInfo(characterId: String)
        fun getComics(characterId: String)
    }

    interface Interactor : BaseContract.Interactor {
        fun fetchComics(characterId: String)
        fun fetchCharacterInfo(characterId: String)

        interface ResponseListener {
            fun onComicsFetched(data: List<Comic>?)
            fun onCharacterInfoFetched(data: List<MarvelItem>?)
            fun onErrorFetchingData(msg: String?)
        }

    }
}
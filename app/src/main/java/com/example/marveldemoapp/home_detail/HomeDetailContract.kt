package com.example.marveldemoapp.home_detail

import com.example.marveldemoapp.base.BaseContract
import com.example.marveldemoapp.models.MarvelItem

interface HomeDetailContract {

    interface View : BaseContract.View {
        fun renderMarvelItems(data: List<MarvelItem>?)
        fun onMarvelItemClicked(item: MarvelItem)
    }

    interface Presenter : BaseContract.Presenter {
        fun getMarvelCharacters()
        fun getMarvelCharacterInfo(item: MarvelItem)
    }

    interface Interactor : BaseContract.Interactor {
        fun fetchMarvelData()
        fun fetchMarvelCharacterInfo(item: MarvelItem)

        interface ResponseListener {
            fun onMarvelDataFetched(data: List<MarvelItem>?)
            fun onErrorFetchingData(msg: String?)
        }
    }
}
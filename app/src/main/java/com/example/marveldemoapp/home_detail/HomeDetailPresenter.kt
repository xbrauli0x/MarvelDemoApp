package com.example.marveldemoapp.home_detail

import com.example.marveldemoapp.base.BasePresenter
import com.example.marveldemoapp.models.MarvelItem

class HomeDetailPresenter(var view: HomeDetailContract.View?) : BasePresenter(view),
    HomeDetailContract.Presenter, HomeDetailContract.Interactor.ResponseListener {

    private val interactor: HomeDetailContract.Interactor by lazy { HomeDetailInteractor(this) }

    override fun getMarvelCharacters(limit: String?) {
        interactor.fetchMarvelData(limit)
    }

    override fun getMarvelCharacterInfo(item: MarvelItem) {

    }

    override fun onMarvelDataFetched(data: List<MarvelItem>?) {
        view?.renderMarvelItems(data)
    }

    override fun onErrorFetchingData(msg: String?) {
        view?.onErrorWhenFetching(msg)
    }

}
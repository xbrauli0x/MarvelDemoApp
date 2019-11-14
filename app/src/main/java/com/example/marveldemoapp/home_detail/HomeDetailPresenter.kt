package com.example.marveldemoapp.home_detail

import com.example.marveldemoapp.base.BasePresenter
import com.example.marveldemoapp.models.MarvelItem

class HomeDetailPresenter(var view: HomeDetailContract.View?) : BasePresenter(view),
    HomeDetailContract.Presenter, HomeDetailContract.Interactor.ResponseListener {

    private val interactor: HomeDetailContract.Interactor by lazy { HomeDetailInteractor(this) }

    override fun getMarvelCharacters() {
        view?.showProgressBar()
        interactor.fetchMarvelData()
    }

    override fun onMarvelDataFetched(data: List<MarvelItem>?) {
        view?.stopProgressBar()
        view?.renderMarvelItems(data)
    }

    override fun onErrorFetchingData(msg: String?) {
        view?.stopProgressBar()
        view?.onErrorWhenFetching(msg)
    }
}
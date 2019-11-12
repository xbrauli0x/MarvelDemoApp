package com.example.marveldemoapp.home_detail

import com.example.marveldemoapp.base.BasePresenter
import com.example.marveldemoapp.models.MarvelItem

class HomeDetailPresenter(var view: HomeDetailContract.View?) : BasePresenter(view),
    HomeDetailContract.Presenter, HomeDetailContract.Interactor.ResponseListener {

    private val interactor: HomeDetailContract.Interactor by lazy { HomeDetailInteractor(this) }

    override fun getMarvelCharacters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMarvelCharacterInfo(item: MarvelItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMarvelDataFetched(data: List<MarvelItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onErrorFetchingData(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
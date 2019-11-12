package com.example.marveldemoapp.home_detail

import com.example.marveldemoapp.models.MarvelItem

class HomeDetailInteractor(var responseListener: HomeDetailContract.Interactor.ResponseListener?) :
    HomeDetailContract.Interactor {

    override fun fetchMarvelData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchMarvelCharacterInfo(item: MarvelItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
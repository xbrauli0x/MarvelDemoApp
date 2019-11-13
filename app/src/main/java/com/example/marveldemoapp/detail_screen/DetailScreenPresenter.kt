package com.example.marveldemoapp.detail_screen

import com.example.marveldemoapp.base.BasePresenter
import com.example.marveldemoapp.models.Comic
import com.example.marveldemoapp.models.MarvelItem

class DetailScreenPresenter(var view: DetailScreenContract.View?) : BasePresenter(view),
    DetailScreenContract.Presenter, DetailScreenContract.Interactor.ResponseListener {

    private val interactor: DetailScreenContract.Interactor by lazy { DetailScreenInteractor(this) }

    override fun getCharacterInfo(characterId: String) {
        interactor.fetchCharacterInfo(characterId)
    }

    override fun getComics(characterId: String) {
        interactor.fetchComics(characterId)
    }

    override fun onComicsFetched(data: List<Comic>?) {
        view?.renderComics(data)
    }

    override fun onCharacterInfoFetched(data: List<MarvelItem>?) {
        val item: MarvelItem? = data?.first()
        view?.renderCharacterInfo(item)
    }

    override fun onErrorFetchingData(msg: String?) {

    }
}
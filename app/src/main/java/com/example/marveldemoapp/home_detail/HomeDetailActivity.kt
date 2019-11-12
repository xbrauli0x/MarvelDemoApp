package com.example.marveldemoapp.home_detail

import android.os.Bundle
import com.example.marveldemoapp.R
import com.example.marveldemoapp.base.BaseActivity
import com.example.marveldemoapp.base.BaseContract
import com.example.marveldemoapp.models.MarvelItem

class HomeDetailActivity : BaseActivity(), HomeDetailContract.View {

    private val presenter: HomeDetailContract.Presenter by lazy { HomeDetailPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getMarvelCharacters()
    }

    override fun getBasePresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun renderMarvelItems(data: List<MarvelItem>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMarvelItemClicked(item: MarvelItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
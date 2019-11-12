package com.example.marveldemoapp.base

open class BasePresenter(private var baseView: BaseContract.View?) : BaseContract.Presenter {

    override fun unBind() {
        this.baseView = null
    }
}
package com.example.marveldemoapp.base

interface BaseContract {

    interface View

    interface Presenter {
        fun unBind()
    }

    interface Interactor
}
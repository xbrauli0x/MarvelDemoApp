package com.example.marveldemoapp.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getBasePresenter(): BaseContract.Presenter?

    override fun onDestroy() {
        super.onDestroy()
        val presenter: BaseContract.Presenter? = getBasePresenter()
        presenter?.unBind()
    }
}
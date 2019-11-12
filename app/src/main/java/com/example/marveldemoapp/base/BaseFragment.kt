package com.example.marveldemoapp.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getBasePresenter(): BaseContract.Presenter?

    override fun onDestroy() {
        super.onDestroy()
        val presenter: BaseContract.Presenter? = getBasePresenter()
        presenter?.unBind()
    }
}
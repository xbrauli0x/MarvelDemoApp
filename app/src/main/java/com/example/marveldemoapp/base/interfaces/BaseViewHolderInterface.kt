package com.example.marveldemoapp.base.interfaces

interface BaseViewHolderInterface<T> {
    fun bind(model: T, onClickedItem: (T) -> Unit)
    fun unBind()
}
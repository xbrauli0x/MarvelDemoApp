package com.example.marveldemoapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldemoapp.base.interfaces.BaseViewHolderInterface

open class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView),
    BaseViewHolderInterface<T> {

    override fun bind(model: T, onClickedItem: (T) -> Unit) {
        itemView.setOnClickListener { onClickedItem(model) }
    }

    override fun unBind() {
        itemView.setOnClickListener(null)
    }

}
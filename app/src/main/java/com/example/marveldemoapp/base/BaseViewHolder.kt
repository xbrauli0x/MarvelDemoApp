package com.example.marveldemoapp.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldemoapp.base.interfaces.BaseViewHolderInterface
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView),
    BaseViewHolderInterface<T>, KoinComponent {

    val context: Context by inject()

    override fun bind(model: T, onClickedItem: (T) -> Unit) {
        itemView.setOnClickListener { onClickedItem(model) }
    }

    override fun unBind() {
        itemView.setOnClickListener(null)
    }

}
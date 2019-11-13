package com.example.marveldemoapp.home_detail

import android.view.View
import com.example.marveldemoapp.base.BaseViewHolder
import com.example.marveldemoapp.models.MarvelItem
import kotlinx.android.synthetic.main.item_marvel_character.view.*

class HomeDetailViewHolder(itemView: View) : BaseViewHolder<MarvelItem>(itemView) {

    override fun bind(model: MarvelItem, onClickedItem: (MarvelItem) -> Unit) {
        super.bind(model, onClickedItem)
        itemView.txtName.text = model.name
    }

}
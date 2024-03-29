package com.example.marveldemoapp.home_detail

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.marveldemoapp.R
import com.example.marveldemoapp.base.BaseViewHolder
import com.example.marveldemoapp.models.MarvelItem
import com.example.marveldemoapp.models.Thumbnail
import kotlinx.android.synthetic.main.item_marvel_character.view.*

class HomeDetailViewHolder(itemView: View) : BaseViewHolder<MarvelItem>(itemView) {

    override fun bind(model: MarvelItem, onClickedItem: (MarvelItem) -> Unit) {
        super.bind(model, onClickedItem)
        itemView.txtName.text = model.name
        loadImage(model)
    }

    private fun loadImage(model: MarvelItem) {
        val thumbnail: Thumbnail? = model.thumbnail
        val url: String? = thumbnail?.url
        Log.d("Img-MarvelItem", "${model.name} : $url")
        Glide.with(itemView)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(itemView.ivMarvelThumbnail)
    }

}
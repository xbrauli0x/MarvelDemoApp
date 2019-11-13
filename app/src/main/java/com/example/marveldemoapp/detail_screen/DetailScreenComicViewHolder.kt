package com.example.marveldemoapp.detail_screen

import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.marveldemoapp.R
import com.example.marveldemoapp.base.BaseViewHolder
import com.example.marveldemoapp.models.Comic
import com.example.marveldemoapp.models.Thumbnail
import kotlinx.android.synthetic.main.item_comic.view.*

class DetailScreenComicViewHolder(itemView: View) : BaseViewHolder<Comic>(itemView) {

    override fun bind(model: Comic, onClickedItem: (Comic) -> Unit) {
        super.bind(model, onClickedItem)
        itemView.txtComicName.text = model.title
        loadImage(model)
    }

    private fun loadImage(model: Comic) {
        val thumbnail: Thumbnail? = model.thumbnail
        val url: String? = thumbnail?.url
        Log.d("Img-Comic", "${model.title} : $url")
        Glide.with(itemView)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(itemView.ivComicImage)
    }
}
package com.example.marveldemoapp.home_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldemoapp.R
import com.example.marveldemoapp.models.MarvelItem

class HomeDetailAdapter(private val onClickListener: (MarvelItem) -> Unit) :
    RecyclerView.Adapter<MarvelItemViewHolder>() {

    private var data: List<MarvelItem>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MarvelItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_marvel_character, viewGroup, false)
        return MarvelItemViewHolder(v)
    }

    override fun onBindViewHolder(marvelItemVH: MarvelItemViewHolder, index: Int) {
        val marvelItem = data?.get(index)
        marvelItemVH.bind(marvelItem!!, onClickListener)
    }

    override fun onViewDetachedFromWindow(holder: MarvelItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unBind()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun updateData(data: List<MarvelItem>?) {
        this.data = data
        notifyDataSetChanged()
    }
}
package com.example.marveldemoapp.home_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldemoapp.R
import com.example.marveldemoapp.models.MarvelItem

class HomeDetailAdapter(private val onClickListener: (MarvelItem) -> Unit) :
    RecyclerView.Adapter<HomeDetailViewHolder>() {

    private var data: List<MarvelItem>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeDetailViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_marvel_character, viewGroup, false)
        return HomeDetailViewHolder(v)
    }

    override fun onBindViewHolder(homeDetailVH: HomeDetailViewHolder, index: Int) {
        val marvelItem = data?.get(index)
        homeDetailVH.bind(marvelItem!!, onClickListener)
    }

    override fun onViewDetachedFromWindow(holder: HomeDetailViewHolder) {
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
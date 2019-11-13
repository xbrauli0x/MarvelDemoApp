package com.example.marveldemoapp.detail_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldemoapp.R
import com.example.marveldemoapp.models.Comic

class DetailScreenComicsAdapter(private val onClickListener: (Comic) -> Unit) :
    RecyclerView.Adapter<DetailScreenComicViewHolder>() {

    private var data: List<Comic>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DetailScreenComicViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_comic, viewGroup, false)
        return DetailScreenComicViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: DetailScreenComicViewHolder, index: Int) {
        val item = data?.get(index)
        viewHolder.bind(item!!, onClickListener)
    }

    override fun onViewDetachedFromWindow(holder: DetailScreenComicViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unBind()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    fun updateData(data: List<Comic>?) {
        this.data = data
        notifyDataSetChanged()
    }
}
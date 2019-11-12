package com.example.marveldemoapp.home_detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marveldemoapp.R
import com.example.marveldemoapp.base.BaseContract
import com.example.marveldemoapp.base.BaseFragment
import com.example.marveldemoapp.home_detail.HomeDetailAdapter
import com.example.marveldemoapp.home_detail.HomeDetailContract
import com.example.marveldemoapp.home_detail.HomeDetailPresenter
import com.example.marveldemoapp.models.MarvelItem
import com.example.marveldemoapp.widgets.GridItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home_detail.*

class HomeDetailFragment : BaseFragment(), HomeDetailContract.View {

    private val presenter: HomeDetailContract.Presenter by lazy { HomeDetailPresenter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpMarvelCharactersRecyclerView()
        fetchMarvelCharacters()
    }


    override fun getBasePresenter(): BaseContract.Presenter? {
        return presenter
    }

    override fun renderMarvelItems(data: List<MarvelItem>?) {
        progressBar.visibility = View.INVISIBLE
        val adapter = rvData.adapter as HomeDetailAdapter
        adapter.updateData(data)
    }

    override fun onMarvelItemClicked(item: MarvelItem) {
        onErrorWhenFetching("Clicked ${item.id} - ${item.name}")
    }

    override fun onErrorWhenFetching(msg: String?) {
        progressBar.visibility = View.INVISIBLE
        Snackbar.make(view!!, msg!!, Snackbar.LENGTH_LONG)
            .setAction(android.R.string.yes, null).show()
    }

    private fun setUpMarvelCharactersRecyclerView() {
        val spanCount = resources.getInteger(R.integer.items_per_column)
        val layoutManager = GridLayoutManager(context, spanCount)
        rvData.layoutManager = layoutManager
        rvData.setHasFixedSize(false)
        rvData.addItemDecoration(GridItemDecoration(10, spanCount))
        val adapter = HomeDetailAdapter {
            onMarvelItemClicked(it)
        }
        rvData.adapter = adapter
    }

    private fun fetchMarvelCharacters() {
        progressBar.visibility = View.VISIBLE
        val limit = resources.getInteger(R.integer.max_items_allowed)
        presenter.getMarvelCharacters(limit.toString())
    }
}
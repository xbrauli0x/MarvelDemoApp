package com.example.marveldemoapp.home_detail

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marveldemoapp.R
import com.example.marveldemoapp.base.BaseActivity
import com.example.marveldemoapp.base.BaseContract
import com.example.marveldemoapp.detail_screen.DetailScreenActivity
import com.example.marveldemoapp.models.MarvelItem
import com.example.marveldemoapp.utils.Utils
import com.example.marveldemoapp.widgets.GridItemDecoration
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.activity_home_detail.*

class HomeDetailActivity : BaseActivity(), HomeDetailContract.View {

    private val presenter: HomeDetailContract.Presenter by lazy { HomeDetailPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (deviceNeedsToBeOnPortraitMode()) {
            forceToPortraitMode()
        }
        setContentView(R.layout.activity_main)
        setUpToolbar()
        val dis:Int = resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        Log.d("DIS Screen", "" + dis)
        setUpMarvelCharactersRecyclerView()
        fetchMarvelCharacters()
    }

    override fun getBasePresenter(): BaseContract.Presenter? {
        return presenter
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun deviceNeedsToBeOnPortraitMode(): Boolean {
        return resources.getBoolean(R.bool.portrait_only)
    }

    private fun forceToPortraitMode() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun renderMarvelItems(data: List<MarvelItem>?) {
        progressBar.visibility = View.INVISIBLE
        val adapter = rvData.adapter as HomeDetailAdapter
        adapter.updateData(data)
    }

    override fun onMarvelItemClicked(item: MarvelItem) {
        onErrorWhenFetching("Clicked ${item.id} - ${item.name}")
        val intent = Intent(this, DetailScreenActivity::class.java)
        intent.putExtra(MarvelItem.TAG, item.id)
        intent.putExtra(MarvelItem.NAME, item.name)
        startActivity(intent)
    }

    override fun onErrorWhenFetching(msg: String?) {
        progressBar.visibility = View.INVISIBLE
        Snackbar.make(coordinator_layout, msg!!, Snackbar.LENGTH_LONG)
            .setAction(android.R.string.yes, null).show()
    }

    private fun setUpMarvelCharactersRecyclerView() {
        val spanCount = resources.getInteger(R.integer.items_per_column)
        val layoutManager = GridLayoutManager(this, spanCount)
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
        presenter.getMarvelCharacters()
    }
}
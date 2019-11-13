package com.example.marveldemoapp.detail_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marveldemoapp.R
import com.example.marveldemoapp.base.BaseActivity
import com.example.marveldemoapp.base.BaseContract
import com.example.marveldemoapp.models.Comic
import com.example.marveldemoapp.models.MarvelItem
import com.example.marveldemoapp.models.Thumbnail
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_screen.*

class DetailScreenActivity : BaseActivity(), DetailScreenContract.View {

    private val presenter: DetailScreenContract.Presenter by lazy { DetailScreenPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        setUpToolbar()
        setUpComicsRecyclerView()
        val characterId: String? = getCharacterId()
        fetchCharacterInfo(characterId)
        fetchComics(characterId)
    }

    override fun getBasePresenter(): BaseContract.Presenter? {
        return presenter
    }

    private fun setUpToolbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val characterName: String? = intent.getStringExtra(MarvelItem.NAME)
        actionBar?.title = characterName
    }

    private fun setUpComicsRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvComicsData.layoutManager = layoutManager
        rvComicsData.setHasFixedSize(false)
        val adapter = DetailScreenComicsAdapter {
            onComicClicked(it)
        }
        rvComicsData.adapter = adapter
    }

    private fun onComicClicked(comic: Comic) {
        showSnackbarMsg("${comic.id} - ${comic.title}")
    }

    private fun showSnackbarMsg(msg: String) {
        val v: View = findViewById(android.R.id.content)
        Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
            .setAction(android.R.string.yes, null).show()
    }

    private fun getCharacterId(): String? {
        return intent.getStringExtra(MarvelItem.TAG)
    }

    private fun fetchCharacterInfo(characterId: String?) {
        presenter.getCharacterInfo(characterId!!)
    }

    private fun fetchComics(characterId: String?) {
        presenter.getComics(characterId!!)
    }

    override fun renderComics(data: List<Comic>?) {
        val adapter = rvComicsData.adapter as DetailScreenComicsAdapter
        adapter.updateData(data)
    }

    override fun renderCharacterInfo(marvelItem: MarvelItem?) {
        txtDetailDescription.text = marvelItem?.description
        val thumbnail: Thumbnail? = marvelItem?.thumbnail
        val url: String? = thumbnail?.url
        Log.d("Img-HeroDetail", "${marvelItem?.name} : $url")
        Glide.with(this)
            .load(url)
            .optionalCenterCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(ivDetailCharacter)
    }
}
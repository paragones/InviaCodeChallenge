package com.inviacodechallenge.parag.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.R
import com.inviacodechallenge.parag.services.ImageLoader
import com.inviacodechallenge.parag.ui.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, SearchView.OnQueryTextListener {
    @Inject lateinit var presenter: MainPresenter

    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.app_title)
        component().inject(this)
        presenter.attach(this)
    }

    private fun doMySearch(query: String) { Log.e(this.javaClass.simpleName, "Query begin : $query")}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.item, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(this);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.isQueryRefinementEnabled = true

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e(this.javaClass.simpleName, "onQueryTextSubmit")
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        presenter.loadRepository(query)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean { return true }

    override fun displayRepositories(categories: List<Repository>) {
    }

    override fun displayError() {
    }

    override fun hideLoading() {
    }
}

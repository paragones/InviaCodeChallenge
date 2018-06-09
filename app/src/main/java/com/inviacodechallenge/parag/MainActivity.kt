package com.inviacodechallenge.parag

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.content.Intent
import android.util.Log


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the intent, verify the action and get the query
        val intent = intent
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            doMySearch(query)
        }
    }

    private fun doMySearch(query: String) { Log.e(this.javaClass.simpleName, "Query begin : $query")}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the options menu from XML
        menuInflater.inflate(R.menu.item, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        // Assumes current activity is the searchable activity
        searchView.setOnQueryTextListener(this);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.isQueryRefinementEnabled = true

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e(this.javaClass.simpleName, "onQueryTextSubmit")
        doMySearch(query)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e(this.javaClass.simpleName, "onQueryTextChange")
        doMySearch(newText)

        return true
    }
}

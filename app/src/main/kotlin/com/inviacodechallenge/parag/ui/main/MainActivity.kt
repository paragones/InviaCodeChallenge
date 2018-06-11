package com.inviacodechallenge.parag.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.inviacodechallenge.parag.R
import com.inviacodechallenge.parag.extensions.gone
import com.inviacodechallenge.parag.extensions.visible
import com.inviacodechallenge.parag.models.RepositoryViewHandler
import com.inviacodechallenge.parag.services.ImageLoader
import com.inviacodechallenge.parag.ui.base.BaseActivity
import com.twotoasters.jazzylistview.effects.SlideInEffect
import com.twotoasters.jazzylistview.recyclerview.JazzyRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, SearchView.OnQueryTextListener {
    private lateinit var mainAdapter: MainAdapter

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component().inject(this)
        setupView()
    }

    private fun setupView() {
        title = getString(R.string.app_title)
        presenter.attach(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.item, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.isQueryRefinementEnabled = true
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        displayLoading()
        presenter.loadRepository(query)
        return true
    }


    override fun onQueryTextChange(query: String): Boolean {
        return true
    }

    override fun displayRepositories(repositoryViewHandler: RepositoryViewHandler) {
        val mainManager = LinearLayoutManager(this)

        mainAdapter = MainAdapter(imageLoader,
                repositoryViewHandler.repositories,
                { fullName: String -> openSubscriberActivity(fullName) })

        mainRecyclerView.layoutManager = mainManager
        mainRecyclerView.adapter = mainAdapter


        val jazzyAnimation = JazzyRecyclerViewScrollListener()
        jazzyAnimation.setShouldOnlyAnimateNewItems(true)
        jazzyAnimation.setTransitionEffect(SlideInEffect())
        mainRecyclerView.addOnScrollListener(jazzyAnimation)
        mainRecyclerView.visible()

    }

    private fun openSubscriberActivity(fullName: String) {
//        startActivity(SubscriberActivity.intent(this))
    }

    override fun displayError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }

    private fun displayLoading() {
        githubIcon.gone()
        githubName.gone()
        tvRepository.gone()
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}

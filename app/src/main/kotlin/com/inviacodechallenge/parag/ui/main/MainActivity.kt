package com.inviacodechallenge.parag.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.Toast
import com.inviacodechallenge.parag.R
import com.inviacodechallenge.parag.SubscriberActivity
import com.inviacodechallenge.parag.extensions.concatenateAlphaAnimations
import com.inviacodechallenge.parag.extensions.gone
import com.inviacodechallenge.parag.extensions.visible
import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryViewHandler
import com.inviacodechallenge.parag.services.ImageLoader
import com.inviacodechallenge.parag.models.Pagination
import com.inviacodechallenge.parag.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, SearchView.OnQueryTextListener {
    private lateinit var mainAdapter: MainAdapter
    private lateinit var query: String
    private lateinit var mainManager: LinearLayoutManager
    private var items: ArrayList<Repository> = ArrayList()
    private var isScrolling: Boolean = false
    private var currentItems: Int = 0
    private var totalItems:Int = 0
    private var scrollOutItems:Int = 0

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component().inject(this)
        setupView()
        setupList()
    }

    private fun setupView() {
        title = getString(R.string.app_title)
        concatenateAlphaAnimations(mutableListOf(githubIcon, githubName, tvRepository), 500, 1f)
        mainManager = LinearLayoutManager(this)
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
        val initialPage = 1
        val initialList = 10

        this.query = query

        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        displayLoading()
        items.clear()
        presenter.loadRepository(query, initialPage, initialList)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        return true
    }

    private fun setupList() {
        mainAdapter = MainAdapter(imageLoader,
                items,
                { repositoryName:String?, ownerName: String? -> openSubscriberActivity(repositoryName, ownerName) })

        mainRecyclerView.layoutManager = mainManager
        mainRecyclerView.adapter = mainAdapter
    }

    override fun displayRepositories(repositoryViewHandler: RepositoryViewHandler) {
        mainRecyclerView.visible()
        items.addAll(repositoryViewHandler.repositories)
        mainAdapter.notifyDataSetChanged()
        addPagination(repositoryViewHandler.pagination)
    }

    private fun addPagination(pagination: Pagination) {
        mainRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = mainManager.childCount
                totalItems = mainManager.itemCount
                scrollOutItems = mainManager.findFirstVisibleItemPosition()

                if (isScrolling && currentItems + scrollOutItems == totalItems) {
                    isScrolling = false
                    if (pagination.hasNext()) {
                        pagination.nextLink?.let {
                            progressBar.visible()
                            presenter.loadRepository(query, it.page, it.perPage)
                        }
                    }
                }
            }
        })
    }

    private fun openSubscriberActivity(repositoryName:String?, ownerName: String?) {
        startActivity(SubscriberActivity.intent(this, repositoryName, ownerName))
    }

    override fun displayError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }

    private fun displayLoading() {
        githubIcon.gone()
        githubName.gone()
        tvRepository.gone()
        mainRecyclerView.gone()
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

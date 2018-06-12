package com.inviacodechallenge.parag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.AbsListView
import android.widget.Toast
import com.inviacodechallenge.parag.extensions.gone
import com.inviacodechallenge.parag.extensions.visible
import com.inviacodechallenge.parag.models.Subscriber
import com.inviacodechallenge.parag.models.SubscriberViewHandler
import com.inviacodechallenge.parag.services.ImageLoader
import com.inviacodechallenge.parag.models.Link
import com.inviacodechallenge.parag.models.Pagination
import com.inviacodechallenge.parag.ui.base.BaseActivity
import com.inviacodechallenge.parag.ui.subscriber.SubscriberAdapter
import com.inviacodechallenge.parag.ui.subscriber.SubscriberPresenter
import com.inviacodechallenge.parag.ui.subscriber.SubscriberView
import kotlinx.android.synthetic.main.activity_subscriber.*
import java.util.*
import javax.inject.Inject

class SubscriberActivity : BaseActivity(), SubscriberView {
    private lateinit var subscriberAdapter: SubscriberAdapter
    private lateinit var repositoryName: String
    private lateinit var ownerName: String
    private lateinit var subscriberManager: GridLayoutManager
    private var items: ArrayList<Subscriber> = ArrayList()
    private var isScrolling: Boolean = false
    private var currentItems: Int = 0
    private var totalItems:Int = 0
    private var scrollOutItems:Int = 0

    @Inject
    lateinit var presenter: SubscriberPresenter

    @Inject
    lateinit var imageLoader: ImageLoader


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscriber)
        component().inject(this)
        setupView()
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.repositoryName = intent.extras.getString(KEY_REPOSITORY_NAME)
        this.ownerName = intent.extras.getString(KEY_OWNER_NAME)

        title = String.format(getString(R.string.subscribers_title),repositoryName)
        presenter.attach(this)
        val threeColumns = 3

        subscriberManager = GridLayoutManager(this, threeColumns)
        subscriberAdapter = SubscriberAdapter(imageLoader, items)

        subscriberRecyclerView.layoutManager = subscriberManager
        subscriberRecyclerView.adapter = subscriberAdapter
        subscriberRecyclerView.visible()

        presenter.loadSubscribers(ownerName, repositoryName)
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun displaySubscribers(subscriberViewHandler: SubscriberViewHandler) {
        subscriberRecyclerView.visible()
        subscriberCount.text = String.format(getString(R.string.subscriber_count),(computeSubscribers(subscriberViewHandler.pagination.lastLink)))
        subscriberCount.visible()
        items.addAll(subscriberViewHandler.subscribers)
        subscriberAdapter.notifyDataSetChanged()
        addPagination(subscriberViewHandler.pagination)
    }

    private fun addPagination(pagination: Pagination) {
        subscriberRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = subscriberManager.childCount
                totalItems = subscriberManager.itemCount
                scrollOutItems = subscriberManager.findFirstVisibleItemPosition()

                if (isScrolling && currentItems + scrollOutItems == totalItems) {
                    isScrolling = false
                    if (pagination.hasNext()) {
                        pagination.nextLink?.let {
                            progressBar.visible()
                            presenter.loadSubscribers(ownerName, repositoryName, it.page, it.perPage)
                        }
                    }
                }
            }
        })
    }

    override fun displayError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }

    private fun computeSubscribers(lastLink: Link?): Int {
        lastLink?.let {
            return it.page * it.perPage
        }

        return 0
    }

    companion object {
        private const val KEY_REPOSITORY_NAME = "KEY_REPOSITORY_NAME"
        private const val KEY_OWNER_NAME = "KEY_OWNER_NAME"

        fun intent(context: Context, repositoryName:String?, ownerName: String?): Intent {
            val intent = Intent(context, SubscriberActivity::class.java)
            intent.putExtras(Bundle().apply {
                repositoryName?.let { putString(KEY_REPOSITORY_NAME, it)}
                ownerName?.let { putString(KEY_OWNER_NAME, it) }
            })
            return intent
        }
    }
}

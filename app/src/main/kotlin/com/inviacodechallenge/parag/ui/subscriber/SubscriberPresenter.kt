package com.inviacodechallenge.parag.ui.subscriber

import com.inviacodechallenge.parag.repositories.GithubRepository
import com.inviacodechallenge.parag.schedulers.ThreadScheduler
import com.inviacodechallenge.parag.ui.base.BasePresenter
import javax.inject.Inject

class SubscriberPresenter @Inject constructor(private val gitHubRepository: GithubRepository,
                                              scheduler: ThreadScheduler) : BasePresenter<SubscriberView>(scheduler) {

    fun loadSubscribers(ownerName: String, repositoryName: String, page: Int = 1, list: Int = 20) {
        gitHubRepository.loadSubscribers(ownerName, repositoryName, page, list)
                .compose(allocateSchedule())
                .subscribe({
                    view?.hideLoading()
                    view?.displaySubscribers(it)
                }, {
                    view?.displayError()
                })
    }
}

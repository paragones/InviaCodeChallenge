package com.inviacodechallenge.parag.ui.main

import com.inviacodechallenge.parag.repositories.GithubRepository
import com.inviacodechallenge.parag.schedulers.ThreadScheduler
import com.inviacodechallenge.parag.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(private val gitHubRepository: GithubRepository,
                                        scheduler: ThreadScheduler) : BasePresenter<MainView>(scheduler) {

    fun loadRepository(query: String, page: Int = 1, list: Int = 10) {
        gitHubRepository.loadRepository(query, page, list)
                .compose(allocateSchedule())
                .subscribe({
                    view?.hideLoading()
                    view?.displayRepositories(it)
                }, {
                    view?.displayError()
                })
    }
}

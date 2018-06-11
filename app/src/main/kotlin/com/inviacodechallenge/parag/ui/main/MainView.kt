package com.inviacodechallenge.parag.ui.main

import com.inviacodechallenge.parag.models.RepositoryViewHandler

interface MainView {
    fun displayRepositories(repositoryViewHandler: RepositoryViewHandler)
    fun displayError()
    fun hideLoading()
}

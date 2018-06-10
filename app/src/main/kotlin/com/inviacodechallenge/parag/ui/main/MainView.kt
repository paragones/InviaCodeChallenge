package com.inviacodechallenge.parag.ui.main

import com.inviacodechallenge.parag.models.Repository

interface MainView {
    fun displayRepositories(categories: List<Repository>)
    fun displayError()
    fun hideLoading()
}

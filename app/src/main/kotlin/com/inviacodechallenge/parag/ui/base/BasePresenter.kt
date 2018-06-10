package com.inviacodechallenge.parag.ui.base

import com.inviacodechallenge.parag.schedulers.ThreadScheduler
import io.reactivex.ObservableTransformer

abstract class BasePresenter<T>(protected var scheduler: ThreadScheduler) {
    protected var view: T? = null

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    protected fun <T> allocateSchedule(): ObservableTransformer<T, T> {
        return scheduler.allocateSchedule<T>()
    }
}
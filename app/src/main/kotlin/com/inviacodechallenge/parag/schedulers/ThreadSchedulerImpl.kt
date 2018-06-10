package com.inviacodechallenge.parag.schedulers

import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler

class ThreadSchedulerImpl(val subscribeOnThread: Scheduler, val observeOnThread: Scheduler) : ThreadScheduler {
    override fun <T> allocateSchedule(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.observeOn(observeOnThread)
                    .subscribeOn(subscribeOnThread)
        }
    }
}
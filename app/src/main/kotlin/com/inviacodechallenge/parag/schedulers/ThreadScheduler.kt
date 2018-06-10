package com.inviacodechallenge.parag.schedulers

import io.reactivex.ObservableTransformer

interface ThreadScheduler {
    fun <T> compose(): ObservableTransformer<T, T>
}
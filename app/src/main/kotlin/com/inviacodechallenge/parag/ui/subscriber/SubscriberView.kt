package com.inviacodechallenge.parag.ui.subscriber

import com.inviacodechallenge.parag.models.SubscriberViewHandler

interface SubscriberView {
    fun hideLoading()
    fun displaySubscribers(subscriberViewHandler: SubscriberViewHandler)
    fun displayError()
}

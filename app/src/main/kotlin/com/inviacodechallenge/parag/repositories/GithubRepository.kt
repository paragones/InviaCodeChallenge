package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.RepositoryViewHandler
import com.inviacodechallenge.parag.models.SubscriberViewHandler
import io.reactivex.Observable

interface GithubRepository {
    fun loadRepository(query: String, page: Int, list: Int): Observable<RepositoryViewHandler>
    fun loadSubscribers(query: String, repositoryName: String, page: Int, list: Int): Observable<SubscriberViewHandler>
}
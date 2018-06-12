package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.RepositoryViewHandler
import io.reactivex.Observable

interface GithubRepository {
    fun loadRepository(query: String, page: Int, list: Int): Observable<RepositoryViewHandler>
}
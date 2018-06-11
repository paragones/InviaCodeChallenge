package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryViewHandler
import io.reactivex.Observable

interface GithubRepository {
    fun loadRepository(query: String): Observable<RepositoryViewHandler>
}
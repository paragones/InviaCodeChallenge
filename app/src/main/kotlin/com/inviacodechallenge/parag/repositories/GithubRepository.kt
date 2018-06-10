package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.Repository
import io.reactivex.Observable

interface GithubRepository {
    fun loadRepository(query: String): Observable<List<Repository>>
}
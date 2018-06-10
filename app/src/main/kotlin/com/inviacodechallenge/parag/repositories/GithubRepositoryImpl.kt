package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResults
import com.inviacodechallenge.parag.rest.GithubRepositoryRest
import com.inviacodechallenge.parag.services.DataMapper
import io.reactivex.Observable

class GithubRepositoryImpl(val githubRepositoryRest: GithubRepositoryRest,
                           val dataMapper: DataMapper<List<RepositoryResults>, List<Repository>>): GithubRepository {
    override fun loadRepository(query: String): Observable<List<Repository>> {
        return githubRepositoryRest.repositories(query)
                .map { dataMapper.transform(it.repositoryResults)}

    }
}
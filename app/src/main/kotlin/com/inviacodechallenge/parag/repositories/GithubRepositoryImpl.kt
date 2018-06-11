package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResponse
import com.inviacodechallenge.parag.models.RepositoryResults
import com.inviacodechallenge.parag.models.RepositoryViewHandler
import com.inviacodechallenge.parag.rest.GithubRepositoryRest
import com.inviacodechallenge.parag.services.DataMapper
import com.inviacodechallenge.parag.services.PaginationHeaderParser
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result

class GithubRepositoryImpl(val githubRepositoryRest: GithubRepositoryRest,
                           val dataMapper: DataMapper<List<RepositoryResults>, List<Repository>>): GithubRepository {
    override fun loadRepository(query: String): Observable<RepositoryViewHandler> {
        return githubRepositoryRest.repositories(query, 1, 5)
                .flatMap { Observable.just(RepositoryViewHandler(paginateResults(it), transformData(it))) }
    }

    private fun paginateResults(it: Result<RepositoryResponse>) =
            PaginationHeaderParser.parse(it.response())

    private fun transformData(it: Result<RepositoryResponse>?): List<Repository> {
            it?.response()?.body()?.let {
                return dataMapper.transform(it.repositoryResults)
            }
        return emptyList()
    }
}
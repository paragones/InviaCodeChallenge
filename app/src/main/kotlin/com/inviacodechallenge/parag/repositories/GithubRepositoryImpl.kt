package com.inviacodechallenge.parag.repositories

import com.inviacodechallenge.parag.models.*
import com.inviacodechallenge.parag.rest.GithubRepositoryRest
import com.inviacodechallenge.parag.services.DataMapper
import com.inviacodechallenge.parag.services.PaginationHeaderParser
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result

class GithubRepositoryImpl(val githubRepositoryRest: GithubRepositoryRest,
                           val mapper: DataMapper): GithubRepository {
    override fun loadRepository(query: String, page: Int, list: Int): Observable<RepositoryViewHandler> {
        return githubRepositoryRest.repositories(query, page, list)
                .flatMap { Observable.just(RepositoryViewHandler(paginateResults(it), transformRepositories(it))) }
    }

    override fun loadSubscribers(query: String, repositoryName: String, page: Int, list: Int): Observable<SubscriberViewHandler> {
        return githubRepositoryRest.subscribers(query, repositoryName, page, list)
                .flatMap { Observable.just(SubscriberViewHandler(paginateResults(it), transformSubscribers(it))) }
    }

    private fun paginateResults(it: Result<*>) =
            PaginationHeaderParser.parse(it.response())

    private fun transformRepositories(it: Result<RepositoryResponse>?): List<Repository> {
            it?.response()?.body()?.let {
                return mapper.transformRepository(it.repositoryResults)
            }
        return emptyList()
    }

    private fun transformSubscribers(it: Result<List<OwnerResults>>?): List<Subscriber> {
        it?.response()?.body()?.let {
            return mapper.tranformSubscribers(it)
        }
        return emptyList()
    }
}
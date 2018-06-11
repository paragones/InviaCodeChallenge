package com.inviacodechallenge.parag.rest

import com.inviacodechallenge.parag.models.RepositoryResponse
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepositoryRest {
    @GET("search/repositories")
    fun repositories(@Query("q") query: String, @Query("page") page: Int ,@Query("per_page") per_page: Int): Observable<Result<RepositoryResponse>>

}

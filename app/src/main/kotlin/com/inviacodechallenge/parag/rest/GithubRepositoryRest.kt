package com.inviacodechallenge.parag.rest

import com.inviacodechallenge.parag.models.OwnerResults
import com.inviacodechallenge.parag.models.RepositoryResponse
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface GithubRepositoryRest {

    @GET("search/repositories")
    fun repositories(@Query("q") query: String,
                     @Query("page") page: Int ,
                     @Query("per_page") per_page: Int): Observable<Result<RepositoryResponse>>

    @GET("repos/{ownerName}/{repositoryName}/subscribers")
    fun subscribers(@Path("ownerName") ownerName: String,
                    @Path("repositoryName") repositoryName: String,
                    @Query("page") page: Int ,
                    @Query("per_page") per_page: Int): Observable<Result<List<OwnerResults>>>

}

package com.inviacodechallenge.parag.modules

import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResults
import com.inviacodechallenge.parag.repositories.GithubRepository
import com.inviacodechallenge.parag.repositories.GithubRepositoryImpl
import com.inviacodechallenge.parag.rest.GithubRepositoryRest
import com.inviacodechallenge.parag.services.DataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(rest: GithubRepositoryRest, mapper: DataMapper):
            GithubRepository = GithubRepositoryImpl(rest,mapper)
}
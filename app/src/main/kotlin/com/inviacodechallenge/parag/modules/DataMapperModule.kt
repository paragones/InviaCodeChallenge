package com.inviacodechallenge.parag.modules

import com.inviacodechallenge.parag.models.Repository
import com.inviacodechallenge.parag.models.RepositoryResults
import com.inviacodechallenge.parag.services.DataMapper
import com.inviacodechallenge.parag.services.RepositoryDataMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataMapperModule {

    @Provides
    @Singleton
    fun provideRepositoryMapper(): DataMapper<List<RepositoryResults>, List<Repository>> = RepositoryDataMapperImpl()
}
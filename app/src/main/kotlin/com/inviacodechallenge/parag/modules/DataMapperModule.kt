package com.inviacodechallenge.parag.modules

import com.inviacodechallenge.parag.services.DataMapper
import com.inviacodechallenge.parag.services.DataMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataMapperModule {

    @Provides
    @Singleton
    fun provideDataMapper(): DataMapper = DataMapperImpl()
}
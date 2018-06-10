package com.inviacodechallenge.parag.modules

import android.content.Context
import com.inviacodechallenge.parag.services.ImageLoader
import com.inviacodechallenge.parag.services.PicasoImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun provideImageLoader(context: Context): ImageLoader = PicasoImageLoader(context)
}
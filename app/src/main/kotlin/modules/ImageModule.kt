package modules

import android.content.Context
import com.inviacodechallenge.parag.image.ImageLoader
import com.inviacodechallenge.parag.image.PicasoImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun provideImageLoader(context: Context): ImageLoader = PicasoImageLoader(context)
}
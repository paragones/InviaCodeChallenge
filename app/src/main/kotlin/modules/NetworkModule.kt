package modules

import android.net.Uri
import com.google.gson.*
import com.inviacodechallenge.parag.BuildConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideDefaultRestAdapter(): Retrofit = defaultRetrofit().build()

    private fun defaultRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(defaultGson()))
    }

    private fun defaultGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Uri::class.java, UriDeserializer())
                .create()
    }

    class UriDeserializer : JsonDeserializer<Uri> {
        @Throws(JsonParseException::class)
        override fun deserialize(src: JsonElement, srcType: Type,
                                 context: JsonDeserializationContext): Uri = Uri.parse(String.format("http:%s",src.asString))
    }
}
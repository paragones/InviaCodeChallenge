package com.inviacodechallenge.parag.modules

import android.net.Uri
import com.google.gson.*
import com.inviacodechallenge.parag.BuildConfig
import com.inviacodechallenge.parag.rest.GithubRepositoryRest
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGithubRepositoryRest(retrofit: Retrofit): GithubRepositoryRest
            = retrofit.create(GithubRepositoryRest::class.java)

    @Provides
    @Singleton
    fun provideDefaultRestAdapter(): Retrofit = defaultRetrofit().build()

    private fun defaultRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(defaultGson()))
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
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
package com.inviacodechallenge.parag.modules

import android.net.Uri
import android.util.Log
import com.google.gson.*
import com.inviacodechallenge.parag.BuildConfig
import com.inviacodechallenge.parag.rest.GithubRepositoryRest
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.BufferedSink
import okio.ByteString
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.nio.Buffer
import javax.inject.Singleton
import okio.BufferedSource


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
//                .addInterceptor(bytesInterceptor)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }

//    private val bytesInterceptor: Interceptor = Interceptor { chain ->
//        val originalRequest = chain.request()
//        val originalRequestBody = originalRequest.body()

//        try {
//            val copy = originalRequest.newBuilder().build()
//            val buffer = okio.Buffer()
//            copy.body()?.writeTo(buffer)
//            Log.e(this.javaClass.simpleName, "buffer.readUtf8() -> ${buffer.readUtf8()}")
//        } catch (e: IOException) {
//            Log.e(this.javaClass.simpleName, "did not work")
//        }


//        val buffer = okio.Buffer()
//        originalRequestBody?.writeTo(buffer)
//        val fixedLengthRequestBody = RequestBody.create(originalRequestBody?.contentType(),buffer.snapshot())
//        val newContent = ByteArray(1024)
//        try {
//            val newRequest = originalRequest.newBuilder()
//                    .method()
//                    .header("Content-Length", newContent.size.toString())
////                    .method(originalRequest.method(), fixedLengthRequestBody)
//                    .build()
//
//            Log.e(this.javaClass.simpleName, "newRequest.headers() : ${newRequest.headers()}")
//            Log.e(this.javaClass.simpleName, "newRequest.method() : ${newRequest.method()}")
//            Log.e(this.javaClass.simpleName, "fixedLengthRequestBody body : ${fixedLengthRequestBody.toString()}")

//        } catch (e: Exception) {
//            Log.e(this.javaClass.simpleName, "error : $e")
//        }
//        chain.proceed(originalRequest)
//        chain.proceed(newRequest)
//    }

    private val bytesInterceptor: Interceptor = Interceptor { chain ->
        val request = chain.request()
//        Log.e(this.javaClass.simpleName, "call ==> " + request.url())
        val response = chain.proceed(request)
        val responseBody = response?.body()
        val source = responseBody?.source()
        source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source?.buffer()
        val newResponse = response.newBuilder()
                .body(ResponseBody.create(responseBody?.contentType(),buffer.toString()))
                .build()


        Log.e(this.javaClass.simpleName, "response? ${response.isSuccessful}")
        Log.e(this.javaClass.simpleName, "response? ${response.message()}")
        Log.e(this.javaClass.simpleName, "responseBody? ${responseBody?.contentType()}")
        Log.e(this.javaClass.simpleName, "responseBody? ${responseBody?.contentLength()}")
        Log.e(this.javaClass.simpleName, "responseBody? ${responseBody.toString()}")


//        val requestBuffer = Buffer()
//        response.body().(requestBuffer)
//        Log.d("OkHttp", requestBuffer.readUtf8())

        newResponse
//        return@Interceptor newResponse
    }

//    class ResponseInterceptor : Interceptor {
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val request = chain.request()
//            Log.e(this.javaClass.simpleName, "call ==> " + request.url())
//            val response = chain.proceed(request)
//            val responseBody = response?.body()
//            val source = responseBody?.source()
//            source?.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
//            val buffer = source?.buffer()
//            val newResponse = response.newBuilder()
//                    .body(ResponseBody.create(responseBody?.contentType(),buffer.toString()))
//                    .build()
//            return newResponse
//        }
//
//    }

    private fun defaultGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Uri::class.java, UriDeserializer())
                .create()
    }

    class UriDeserializer : JsonDeserializer<Uri> {
        @Throws(JsonParseException::class)
        override fun deserialize(src: JsonElement, srcType: Type,
                                 context: JsonDeserializationContext): Uri = Uri.parse(src.asString)
//                                 context: JsonDeserializationContext): Uri = Uri.parse(String.format("http:%s",src.asString))
    }
}
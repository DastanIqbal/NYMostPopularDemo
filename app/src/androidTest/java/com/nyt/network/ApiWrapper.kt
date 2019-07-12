package com.nyt.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nyt.mostpopular.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiWrapper {
    private val TAG = this::class.java.simpleName
    private var nytApi: INytApi? = null

    @JvmStatic
    var SERVICE_URL = "https://"

    private fun createService(): INytApi? {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

            .client(okHttpClientBuilder().build())
            .build().create(INytApi::class.java)
    }

    private fun okHttpClientBuilder(): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val originalRequest = chain.request()

                val httpUrl = originalRequest
                    .url
                    .newBuilder()
                    .addQueryParameter("api-key", "testApiKey").build()

                val newRequest = originalRequest.newBuilder().url(httpUrl).build()
                chain.proceed(newRequest)
            }
        if (BuildConfig.FLAVOR == "dev" && BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(interceptor).build()
        }
        return okHttpClientBuilder
    }

    fun getAPI(): INytApi {
        if (nytApi != null)
            return nytApi as INytApi
        nytApi = createService()
        return nytApi as INytApi
    }
}
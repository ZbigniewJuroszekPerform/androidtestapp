package com.sample.perform.app.injection.module

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sample.perform.app.BuildConfig
import com.sample.perform.app.data.api.base.HeaderRequestInterceptor
import com.sample.perform.app.data.api.news.NewsRetrofitApi

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    val ENDPOINT = "http://omnisport-article.performfeeds.com/"

    companion object {
        const val CONNECTION_TIMEOUT_IN_SECONDS = 30L
    }

    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderRequestInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                 httpClientBuilder
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()
        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor()
            = HeaderRequestInterceptor()


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    @Provides
    @Singleton
    fun provideRibotsService(okHttpClient: OkHttpClient, gson: Gson): NewsRetrofitApi {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(NewsRetrofitApi::class.java)
    }


}
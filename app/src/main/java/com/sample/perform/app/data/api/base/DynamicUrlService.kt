package com.sample.perform.app.data.api.base

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class DynamicUrlService<T>(val client: OkHttpClient,
                                    val converter: Converter.Factory = GsonConverterFactory.create()) {

    private var restAdapter: T? = null
    private var currentBaseUrl: String? = null

    protected fun restAdapter(baseUrl: String): T {
        val refinedBaseUrl = if (baseUrl.endsWith("/").not()) {
            baseUrl + "/"
        } else {
            baseUrl
        }
        if (restAdapter == null || refinedBaseUrl != currentBaseUrl) {
            currentBaseUrl = refinedBaseUrl
            restAdapter = buildRetrofitAPI(getGenericParameter(), client)
        }

        return restAdapter!!
    }

    protected abstract fun getGenericParameter(): Class<T>

    private fun buildRetrofitAPI(clazz: Class<T>, client: OkHttpClient): T {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(currentBaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(converter)
                .build()
                .create(clazz)
    }
}


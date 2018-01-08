package com.sample.perform.app.data.api.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseService<T>(baseUrl: String, client: OkHttpClient) {

    private var restAdapter: T

    init {
        val schemaClass = getGenericParameter()
        restAdapter = buildRetrofitAPI(schemaClass, baseUrl, client)
    }

    protected fun restAdapter() = restAdapter

    protected abstract fun getGenericParameter(): Class<T>

    private fun buildRetrofitAPI(clazz: Class<T>, baseUrl: String, client: OkHttpClient): T {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(clazz)
    }
}
package com.sample.perform.app.data

import com.sample.perform.app.data.api.news.NewsRetrofitApi
import com.sample.perform.app.data.model.Response
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val newsService: NewsRetrofitApi) {

    fun getNews(): Observable<Response> {
        return newsService.getNews()
    }
}
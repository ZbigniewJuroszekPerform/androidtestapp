package com.sample.perform.app.data.api.news

import com.sample.perform.app.data.model.Response
import io.reactivex.Single

interface NewsBackendApi {
    fun getNews(baseUrl: String): Single<Response>
}
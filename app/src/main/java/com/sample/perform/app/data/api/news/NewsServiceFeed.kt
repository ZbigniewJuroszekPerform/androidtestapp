package com.sample.perform.app.data.api.news

import com.sample.perform.app.data.api.base.DynamicUrlService
import com.sample.perform.app.data.model.Response
import okhttp3.OkHttpClient
import rx.Single



class NewsServiceFeed (client: OkHttpClient) : DynamicUrlService<NewsRetrofitApi>(client), NewsBackendApi {

    override fun getNews(baseUrl: String) = restAdapter(baseUrl).getNews()

    override fun getGenericParameter(): Class<NewsRetrofitApi> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
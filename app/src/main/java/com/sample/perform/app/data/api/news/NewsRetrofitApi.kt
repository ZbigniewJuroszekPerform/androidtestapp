package com.sample.perform.app.data.api.news

import com.sample.perform.app.data.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface NewsRetrofitApi {
    @GET("?format=json&groupId=\'home\'")
    fun getNews(): Single<Response>
}
package com.sample.perform.app.data.api.news

import com.sample.perform.app.data.model.Response
import rx.Observable
import retrofit2.http.GET


interface NewsRetrofitApi {
    @GET("article/1odpqdik0zvty15ttjudoee8al?_fmt=json&_rt=c")
    fun getNews(): Observable<Response>
}
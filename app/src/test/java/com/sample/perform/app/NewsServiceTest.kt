package com.sample.perform.app

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.whenever
import com.sample.perform.app.data.api.news.NewsBackendApi
import com.sample.perform.app.data.model.Response
import com.sample.perform.app.data.service.news.NewsService
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.sample.perform.app.data.model.ArticlesItem
import java.util.*



class NewsServiceTest {

    private lateinit var systemUnderTest: NewsService

    private  val newsBackendApi : NewsBackendApi = mock()

        @Before
        fun setUp(){
            whenever(newsBackendApi.getNews(TestDataFactory.newsUrl))
                    .thenReturn(Single.just(TestDataFactory.makeResponseList(5)))
            systemUnderTest = NewsService(newsBackendApi)
        }

   @Test
    fun loadAllNews (){
       val newsObserver = systemUnderTest.getNews().test()
       newsObserver.assertNoErrors()
       newsObserver.assertValueCount(1)
       newsObserver.assertComplete()

    }

}
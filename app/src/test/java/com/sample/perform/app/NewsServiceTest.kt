package com.sample.perform.app


import com.nhaarman.mockito_kotlin.*
import com.sample.perform.app.data.api.news.NewsBackendApi
import com.sample.perform.app.data.model.Response
import com.sample.perform.app.data.service.news.NewsService

import org.junit.Before
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers
import rx.Observable
import rx.Single


class NewsServiceTest {

    private lateinit var systemUnderTest: NewsService
    private  val newsBackendApi : NewsBackendApi = mock()

        @Before
        fun setUp(){
            whenever(newsBackendApi.getNews(any()))
                    .thenReturn(Observable.just(TestDataFactory.makeResponseList(5)))
            systemUnderTest = NewsService(newsBackendApi)
        }

    @Test
    fun checkIfEndpointHasCorrectBaseUrl (){
        val urlCaptor = argumentCaptor<String>()
        whenever(newsBackendApi.getNews(urlCaptor.capture()))
                .thenReturn(Observable.just(TestDataFactory.makeResponseList(5)))
        val newsObserver = systemUnderTest.getNews().test()
        assertEquals("http://omnisport-article.performfeeds.com/",urlCaptor.firstValue)
    }

   @Test
    fun loadAllNews (){
       val newsObserver = systemUnderTest.getNews().test()
       newsObserver.assertNoErrors()
       newsObserver.assertValueCount(1)
       newsObserver.assertCompleted()
    }

    @Test
    fun loadAllNewsErrorHandling (){
        whenever(newsBackendApi.getNews(TestDataFactory.newsUrl))
                .thenReturn(Observable.error<Response>(RuntimeException()))
        val newsObserver = systemUnderTest.getNews().test()
        newsObserver.assertCompleted()
    }

}
package com.sample.perform.app


import com.nhaarman.mockito_kotlin.*
import com.sample.perform.app.data.DataManager
import com.sample.perform.app.data.api.news.NewsRetrofitApi

import com.sample.perform.app.data.model.Response

import org.junit.Before
import org.junit.Test
import rx.Observable


class NewsServiceTest {
    private  val mockNewsServiceApi : NewsRetrofitApi = mock()
    lateinit var systemUnderTest: DataManager

        @Before
        fun setUp(){
            whenever(mockNewsServiceApi.getNews())
                    .thenReturn(Observable.just(TestDataFactory.makeResponseList(5)))
            systemUnderTest = DataManager(mockNewsServiceApi)
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
            whenever(systemUnderTest.getNews()).thenReturn(Observable.error<Response>(RuntimeException()))
        val newsObserver = systemUnderTest.getNews().test()
        newsObserver.assertNotCompleted()
    }

    /*   @Test
   fun checkIfEndpointHasCorrectBaseUrl (){
       val urlCaptor = argumentCaptor<String>()
       whenever(mockNewsServiceApi.getNews(urlCaptor.capture()))
               .thenReturn(Observable.just(TestDataFactory.makeResponseList(5)))
       val newsObserver = systemUnderTest.getNews().test()
       assertEquals("http://omnisport-article.performfeeds.com/",urlCaptor.firstValue)
   }*/


}
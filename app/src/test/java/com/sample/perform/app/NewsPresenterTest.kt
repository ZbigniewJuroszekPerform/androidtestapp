package com.sample.perform.app

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.sample.perform.app.data.DataManager
import com.sample.perform.app.data.api.news.NewsRetrofitApi
import com.sample.perform.app.data.model.Response
import com.sample.perform.app.ui.news.NewsContract
import com.sample.perform.app.ui.news.NewsPresenter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable


@RunWith(MockitoJUnitRunner::class)
class NewsPresenterTest {

    @Rule @JvmField
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock
    lateinit var  mockNewsMvpView: NewsContract.View


    lateinit var  mockDataManager: DataManager

    private  val mockNewsServiceApi : NewsRetrofitApi = mock()
    lateinit var mockDatamanager: DataManager

   // private  val mockNewsServiceApi : NewsRetrofitApi = mock()

    lateinit var newsPresenter: NewsPresenter

    @Before
    fun setUp() {
        mockDataManager = DataManager(mockNewsServiceApi)
       newsPresenter = NewsPresenter(mockDataManager)
      newsPresenter.attachView(mockNewsMvpView)
    }

    @After
    fun tearDown() {
       newsPresenter.detachView()
    }

    @Test
    fun loadNewsReturnsNews() {
        val news = TestDataFactory.makeResponseList(10)
        whenever(mockDataManager.getNews()).thenReturn(Observable.just(news))
        newsPresenter.loadNews()
        Mockito.verify(mockNewsMvpView).showNews(news)
        Mockito.verify(mockNewsMvpView, Mockito.never()).showNewsEmpty()
        Mockito.verify(mockNewsMvpView, Mockito.never()).showError()
    }

    @Test
    fun loadNewsReturnsEmptyList() {
        val emptyNewsList = TestDataFactory.makeResponseEmptyList(0)
        whenever(mockDataManager.getNews()).thenReturn(Observable.just(emptyNewsList))
        newsPresenter.loadNews()
        Mockito.verify(mockNewsMvpView).showNewsEmpty()
        Mockito.verify(mockNewsMvpView, Mockito.never()).showNews(any())
        Mockito.verify(mockNewsMvpView, Mockito.never()).showError()
    }

    @Test
    fun loadNewsFails() {
        whenever(mockDataManager.getNews()).thenReturn(Observable.error<Response>(RuntimeException()))
        newsPresenter.loadNews()
        Mockito.verify(mockNewsMvpView).showError()
        Mockito.verify(mockNewsMvpView, Mockito.never()).showNewsEmpty()
        Mockito.verify(mockNewsMvpView, Mockito.never()).showNews(any())
    }

}

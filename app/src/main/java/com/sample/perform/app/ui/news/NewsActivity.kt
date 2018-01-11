package com.sample.perform.app.ui.news

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.sample.perform.app.R
import com.sample.perform.app.data.model.Response
import com.sample.perform.app.ui.base.BaseActivity

import timber.log.Timber
import javax.inject.Inject

class NewsActivity : BaseActivity(), NewsContract.View {


    @Inject lateinit var newsPresenter: NewsPresenter
    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    @Inject lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_news)
        ButterKnife.bind(this)
        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        newsPresenter.attachView(this)
        newsPresenter.loadNews()
    }

    override fun onResume() {
        super.onResume()/**/
    }

    override fun onDestroy() {
        super.onDestroy()
        newsPresenter.detachView()
    }

    override fun showError() {
        Timber.e( "There was an error loading the news.")
    }

    override fun showNews(response: Response) {
        newsAdapter.items = response.articles
        newsAdapter.notifyDataSetChanged()
        Timber.e( " loading the news." + response.articles)
    }

    override fun showNewsEmpty() {
        newsAdapter.items = emptyList()
        newsAdapter.notifyDataSetChanged()
        Timber.e( "There was no loading the news.")
    }


}

package com.sample.perform.app.ui.news

import com.sample.perform.app.data.DataManager

import com.sample.perform.app.injection.ConfigPersistent
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


@ConfigPersistent
class NewsPresenter
@Inject
constructor (private val dataManager: DataManager) : NewsContract.Presenter() {

    private var subscription: Subscription? = null

    override fun detachView() {
        super.detachView()
        subscription?.unsubscribe()
    }

    override fun loadNews() {
        subscription?.unsubscribe()
        subscription = dataManager.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onNext = { if (it.articles!!.isEmpty()) view.showNewsEmpty() else view.showNews(it) },
                        onError = {
                            Timber.e(it, "There was an error loading the news.")
                            view.showError()
                        }
                )

    }

}
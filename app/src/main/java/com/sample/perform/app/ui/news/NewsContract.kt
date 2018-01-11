package com.sample.perform.app.ui.news

import com.facebook.stetho.inspector.protocol.module.Network
import com.sample.perform.app.data.model.Response
import com.sample.perform.app.ui.base.BasePresenter
import com.sample.perform.app.ui.base.MvpView


object NewsContract {

    interface View : MvpView {
        fun showNews(response: Response)
        fun showNewsEmpty()
        fun showError()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun loadNews()
    }
}
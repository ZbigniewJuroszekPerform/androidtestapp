package com.sample.perform.app.injection.module
import android.app.Application
import android.content.Context
import com.sample.perform.app.data.DataManager
import com.sample.perform.app.data.api.news.NewsRetrofitApi
import com.sample.perform.app.injection.ApplicationContext
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class,NetworkModule::class))
interface ApplicationComponent {
    @ApplicationContext fun context(): Context
    fun application(): Application
    fun newsService(): NewsRetrofitApi
    fun dataManager(): DataManager
}


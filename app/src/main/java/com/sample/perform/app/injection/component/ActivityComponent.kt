package com.sample.perform.app.injection.module
import com.sample.perform.app.injection.PerActivity
import com.sample.perform.app.ui.news.NewsActivity
import com.sample.perform.app.ui.news.NewsPresenter
import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(newsActivity: NewsActivity)


}

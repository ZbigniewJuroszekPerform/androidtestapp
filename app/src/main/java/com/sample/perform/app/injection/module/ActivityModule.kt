package com.sample.perform.app.injection.module
import android.app.Activity
import android.content.Context
import com.sample.perform.app.injection.ActivityContext
import com.sample.perform.app.injection.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }


}

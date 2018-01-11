package com.sample.perform.app
import android.app.Application
import android.content.Context
import android.support.annotation.VisibleForTesting
import com.sample.perform.app.injection.module.ApplicationComponent
import com.sample.perform.app.injection.module.ApplicationModule
import com.sample.perform.app.injection.module.DaggerApplicationComponent

open class SampleApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
    }

    fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}

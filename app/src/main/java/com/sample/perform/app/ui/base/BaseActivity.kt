package com.sample.perform.app.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sample.perform.app.SampleApplication
import com.sample.perform.app.injection.module.ActivityComponent
import com.sample.perform.app.injection.module.ActivityModule
import com.sample.perform.app.injection.module.ConfigPersistentComponent
import com.sample.perform.app.injection.module.DaggerConfigPersistentComponent
import timber.log.Timber
import java.util.HashMap
import java.util.concurrent.atomic.AtomicLong

open class BaseActivity: AppCompatActivity() {

    companion object {
        @JvmStatic private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        @JvmStatic private val NEXT_ID = AtomicLong(0)
        @JvmStatic private val componentsMap = HashMap<Long, ConfigPersistentComponent>()
    }

    private var activityId: Long = 0
    lateinit var activityComponent: ActivityComponent
        get

    @Suppress("UsePropertyAccessSyntax")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()

        if (componentsMap[activityId] != null)
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)

        val configPersistentComponent = componentsMap.getOrPut(activityId, {
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)

            val component = (applicationContext as SampleApplication).applicationComponent

            DaggerConfigPersistentComponent.builder()
                    .applicationComponent(component)
                    .build()
        })

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
            componentsMap.remove(activityId)
        }
        super.onDestroy()
    }
}

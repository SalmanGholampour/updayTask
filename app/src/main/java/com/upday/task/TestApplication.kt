package com.upday.task

import android.app.Activity
import android.app.Application
import com.upday.task.data.DataManager
import com.upday.task.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector {


    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    @Inject
    internal lateinit var dataManager: DataManager

    @Inject
    internal lateinit var mCalligraphyConfig: CalligraphyConfig


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }



    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)


        CalligraphyConfig.initDefault(mCalligraphyConfig)

    }
}
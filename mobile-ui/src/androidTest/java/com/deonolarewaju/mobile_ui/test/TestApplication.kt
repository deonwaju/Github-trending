package com.deonolarewaju.mobile_ui.test

import android.app.Activity
import android.app.Application
import android.support.test.InstrumentationRegistry
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import com.deonolarewaju.mobile_ui.di.DaggerTestAppComponent
import com.deonolarewaju.mobile_ui.di.TestAppComponent
import javax.inject.Inject

class TestApplication: Application(), HasActivityInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestAppComponent

    companion object {
        fun appComponent(): TestAppComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext
                    as TestApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestAppComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}
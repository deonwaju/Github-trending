package com.deonolarewaju.mobile_ui.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.deonolarewaju.mobile_ui.GithubTrendingApplication
import com.deonolarewaju.mobile_ui.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        CacheModule::class,
        DataModule::class,
        PresentationModule::class,
        RemoteModule::class,
        UiModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GithubTrendingApplication)
}
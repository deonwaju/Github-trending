package com.deonolarewaju.mobile_ui.di

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import com.deonolarewaju.cache.db.ProjectsDatabase
import com.deonolarewaju.data.repository.ProjectsCache

@Module
object TestCacheModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): ProjectsDatabase {
        return ProjectsDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideProjectsCache(): ProjectsCache {
        return mock()
    }

}
package com.deonolarewaju.mobile_ui.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.deonolarewaju.cache.ProjectsCacheImpl
import com.deonolarewaju.cache.db.ProjectsDatabase
import com.deonolarewaju.data.repository.ProjectsCache

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): ProjectsDatabase {
            return ProjectsDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}
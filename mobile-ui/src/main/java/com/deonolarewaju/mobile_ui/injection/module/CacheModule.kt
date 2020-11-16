package com.deonolarewaju.mobile_ui.injection.module

import android.app.Application
import com.deonolarewaju.cache.ProjectsCacheImpl
import com.deonolarewaju.cache.db.ProjectsDatabase
import com.deonolarewaju.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

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
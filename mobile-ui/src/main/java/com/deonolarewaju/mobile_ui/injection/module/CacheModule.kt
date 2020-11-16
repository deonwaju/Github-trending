package com.deonolarewaju.mobile_ui.injection.module

import android.app.Application
import com.deonolarewaju.cache.ProjectCacheImpl
import com.deonolarewaju.cache.db.ProjectDataBase
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
        fun providesDataBase(application: Application): ProjectDataBase {
            return ProjectDataBase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindProjectsCache(projectsCache: ProjectCacheImpl): ProjectsCache
}
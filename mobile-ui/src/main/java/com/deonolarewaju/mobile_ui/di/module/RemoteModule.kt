package com.deonolarewaju.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import com.deonolarewaju.data.repository.ProjectsRemote
import com.deonolarewaju.mobile_ui.BuildConfig
import com.deonolarewaju.remote.ProjectsRemoteImpl
import com.deonolarewaju.remote.service.GithubTrendingServiceFactory
import com.deonolarewaju.remote.service.GithubTrendingService

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): GithubTrendingService {
            return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}
package com.deonolarewaju.mobile_ui.injection.module

import com.deonolarewaju.data.repository.ProjectsRemote
import com.deonolarewaju.mobile_ui.BuildConfig
import com.deonolarewaju.remote.ProjectsRemoteImpl
import com.deonolarewaju.remote.service.GithubTrendingService
import com.deonolarewaju.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

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
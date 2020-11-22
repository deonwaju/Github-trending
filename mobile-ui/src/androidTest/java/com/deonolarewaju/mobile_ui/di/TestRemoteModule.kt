package com.deonolarewaju.mobile_ui.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import com.deonolarewaju.data.repository.ProjectsRemote
import com.deonolarewaju.remote.service.GithubTrendingService

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGithubService(): GithubTrendingService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideProjectsRemote(): ProjectsRemote {
        return mock()
    }
}
package com.deonolarewaju.mobile_ui.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import com.deonolarewaju.domain.repository.ProjectsRepository
import javax.inject.Singleton

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideDataRepository(): ProjectsRepository {
        return mock()
    }
}
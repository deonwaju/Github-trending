package com.deonolarewaju.mobile_ui.di.module

import dagger.Binds
import dagger.Module
import com.deonolarewaju.data.ProjectsDataRepository
import com.deonolarewaju.domain.repository.ProjectsRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}
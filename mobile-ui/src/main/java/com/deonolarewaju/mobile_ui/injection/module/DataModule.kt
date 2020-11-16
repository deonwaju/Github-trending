package com.deonolarewaju.mobile_ui.injection.module

import com.deonolarewaju.data.ProjectsDataRepository
import com.deonolarewaju.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository): ProjectsRepository
}
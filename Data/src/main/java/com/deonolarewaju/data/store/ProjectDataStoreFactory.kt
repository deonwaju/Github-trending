package com.deonolarewaju.data.store

import com.deonolarewaju.data.repository.ProjectDataStore
import com.deonolarewaju.data.repository.ProjectsCache
import javax.inject.Inject

open class ProjectDataStoreFactory @Inject constructor(
    private val projectCacheDataStore: ProjectCacheDataStore,
    private val projectRemoteDataStore: ProjectRemoteDataStore
){

    open fun getDataStore(projectsCached: Boolean, cacheExpired: Boolean): ProjectDataStore{
        return if (projectsCached && !cacheExpired) {
            projectCacheDataStore
        }else {
            projectRemoteDataStore
        }

    }

    open fun getCacheDataStore(): ProjectDataStore {
        return projectCacheDataStore
    }

}
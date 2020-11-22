package com.deonolarewaju.data.store

import javax.inject.Inject

class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {
    fun getDataStore(projectsCached: Boolean,
                          cacheExpired: Boolean): ProjectsDataStore {
        return if (projectsCached && !cacheExpired) {
            projectsCacheDataStore
        } else {
            projectsRemoteDataStore
        }
    }

    fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }
}
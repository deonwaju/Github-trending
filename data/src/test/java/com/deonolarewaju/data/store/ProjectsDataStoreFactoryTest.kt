package com.deonolarewaju.data.store

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class ProjectsDataStoreFactoryTest {
    @Mock lateinit var cacheStore: ProjectsCacheDataStore
    @Mock lateinit var remoteStore: ProjectsRemoteDataStore
    private lateinit var factory: ProjectsDataStoreFactory

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        factory = ProjectsDataStoreFactory(cacheStore, remoteStore)
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }
}
package com.deonolarewaju.data.store

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectsRemote
import com.deonolarewaju.data.test.factory.DataFactory
import com.deonolarewaju.data.test.factory.ProjectFactory

@RunWith(JUnit4::class)
class ProjectsRemoteDataStoreTest {

    private val remote = mock<ProjectsRemote>()
    private val store = ProjectsRemoteDataStore(remote)

    @Test
    fun getProjectsCompletes() {
        stubRemoteGetProjects(Flowable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsRemote() {
        stubRemoteGetProjects(Flowable.just(listOf(ProjectFactory.makeProjectEntity())))
        store.getProjects()
        verify(remote).getProjects()
    }

    @Test
    fun getProjectsReturnsData() {
        val data = listOf(ProjectFactory.makeProjectEntity())
        stubRemoteGetProjects(Flowable.just(data))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowsException() {
        store.saveProjects(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowsException() {
        store.clearProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowsException() {
        store.getBookmarkedProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowsException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowsException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }

    private fun stubRemoteGetProjects(flowable: Flowable<List<ProjectEntity>>) {
        whenever(store.getProjects())
                .thenReturn(flowable)
    }
}
package com.deonolarewaju.data.store

import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectRemote
import com.deonolarewaju.data.test.factory.DataFactory
import com.deonolarewaju.data.test.factory.ProjectFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectRemoteDataStoreTest {

    private val remote = mock<ProjectRemote>()
    private val store = ProjectRemoteDataStore(remote)

    @Test
    fun getProjectsCompletes() {
        stubRemoteGetProjects(Observable.just(listOf(ProjectFactory.makeProjectEntity())))
        val testObserver = store.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnData() {
        val response = listOf(ProjectFactory.makeProjectEntity())
        stubRemoteGetProjects(Observable.just(response))
        val testObserver = store.getProjects().test()
        testObserver.assertValue(response)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveProjectsThrowException() {
        store.saveProjects(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearProjectsThrowException() {
        store.clearProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getBookmarkedProjectsThrowException() {
        store.getBookmarkedProjects().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsBookmarkedThrowException() {
        store.setProjectAsBookmarked(DataFactory.randomString()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun setProjectAsNotBookmarkedThrowException() {
        store.setProjectAsNotBookmarked(DataFactory.randomString()).test()
    }



    private fun stubRemoteGetProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.getProjects()).thenReturn(observable)
    }


}
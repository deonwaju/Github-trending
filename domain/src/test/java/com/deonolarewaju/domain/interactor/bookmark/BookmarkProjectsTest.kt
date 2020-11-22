package com.deonolarewaju.domain.interactor.bookmark

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.repository.ProjectsRepository
import com.deonolarewaju.domain.test.ProjectDataFactory

class BookmarkProjectsTest {

    private lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var projectsRepository: ProjectsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectsRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {
        val project = ProjectDataFactory.makeProject()
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkProject.createCompletable(
                BookmarkProject.Params.forProject(project.id)
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun bookmarkProjectThrowsException() {
        bookmarkProject.createCompletable().test()
    }

    private fun stubBookmarkProject(completable: Completable) {
        whenever(projectsRepository.bookmarkProject(any()))
                .thenReturn(completable)
    }
}
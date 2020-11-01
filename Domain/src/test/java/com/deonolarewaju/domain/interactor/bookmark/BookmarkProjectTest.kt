package com.deonolarewaju.domain.interactor.bookmark

import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.interactor.bookmarked.BookmarkProject
import com.deonolarewaju.domain.repository.ProjectRepository
import com.deonolarewaju.domain.test.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException

class BookmarkProjectTest {
    private lateinit var bookmarkProject: BookmarkProject
    @Mock lateinit var projectRepository: ProjectRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        bookmarkProject = BookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun bookmarkProjectCompletes() {
        stubBookmarkProject(Completable.complete())
        val testObserver = bookmarkProject.buildUseCaseCompletable(
            BookmarkProject.Params.forProject(ProjectDataFactory.randomUuid())).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalAccessException::class)
    fun bookmarkProjectThrowsException() {
        bookmarkProject.buildUseCaseCompletable().test()
    }

    private fun stubBookmarkProject(completable: Completable){
        whenever(projectRepository.bookmarkProject(any()))
            .thenReturn(completable)
    }

}
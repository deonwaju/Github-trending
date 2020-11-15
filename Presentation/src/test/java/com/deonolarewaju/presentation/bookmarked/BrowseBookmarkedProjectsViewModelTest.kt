package com.deonolarewaju.presentation.bookmarked

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.deonolarewaju.domain.interactor.bookmarked.GetBookmarkedProjects
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.presentation.browse.BrowseBookmarkedViewModel
import com.deonolarewaju.presentation.mapper.ProjectViewMapper
import com.deonolarewaju.presentation.model.ProjectView
import com.deonolarewaju.presentation.state.ResourceState
import com.deonolarewaju.presentation.test.factory.DataFactory
import com.deonolarewaju.presentation.test.factory.ProjectFactory
import com.nhaarman.mockito_kotlin.*
import junit.framework.Assert.assertEquals
import io.reactivex.observers.DisposableObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import java.lang.RuntimeException

@RunWith(JUnit4::class)
class BrowseBookmarkedProjectsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var getBookmarkedProjects = mock<GetBookmarkedProjects>()
    var mapper = mock<ProjectViewMapper>()
    var projectsViewModel = BrowseBookmarkedViewModel(getBookmarkedProjects, mapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun fetchProjectsExecutesUseCase() {
        projectsViewModel.fetchProjects()
        verify(getBookmarkedProjects, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchProjectReturnsSuccess() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperToView(projectViews[0], projects[0])
        stubProjectMapperToView(projectViews[1], projects[1])

        projectsViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(ResourceState.SUCCESS, projectsViewModel.getProjects().value?.status)
    }

    @Test
    fun fetchProjectReturnsData() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)

        stubProjectMapperToView(projectViews[0], projects[0])
        stubProjectMapperToView(projectViews[1], projects[1])

        projectsViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(projectViews, projectsViewModel.getProjects().value?.data)
    }

    @Test
    fun fetchProjectReturnsError() {

        projectsViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR, projectsViewModel.getProjects().value?.status)
    }

    @Test
    fun fetchProjectReturnsErrorMessage() {

        val errorMessage = DataFactory.randomString()

        projectsViewModel.fetchProjects()
        verify(getBookmarkedProjects).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, projectsViewModel.getProjects().value?.message)
    }

    private fun stubProjectMapperToView(projectView: ProjectView, project: Project) {
        whenever(mapper.mapToView(project)).thenReturn(projectView)
    }

}
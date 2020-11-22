package com.deonolarewaju.presentation.browse

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.DisposableObserver
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import com.deonolarewaju.domain.interactor.bookmark.BookmarkProject
import com.deonolarewaju.domain.interactor.bookmark.UnbookmarkProject
import com.deonolarewaju.domain.interactor.browse.GetProjects
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.presentation.BrowseProjectsViewModel
import com.deonolarewaju.presentation.mapper.ProjectViewMapper
import com.deonolarewaju.presentation.model.ProjectView
import com.deonolarewaju.presentation.state.ResourceState
import com.deonolarewaju.presentation.test.factory.DataFactory
import com.deonolarewaju.presentation.test.factory.ProjectFactory

@RunWith(JUnit4::class)
class BrowseProjectsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var getProjects = mock<GetProjects>()
    private var bookmarkProject = mock<BookmarkProject>()
    private var unbookmarkProject = mock<UnbookmarkProject>()
    private var mapper = mock<ProjectViewMapper>()
    private lateinit var projectViewModel: BrowseProjectsViewModel

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            _ -> Schedulers.trampoline()
        }
        projectViewModel = BrowseProjectsViewModel(getProjects, mapper, bookmarkProject, unbookmarkProject)
    }

    @Test
    fun fetchProjectsExecutesUseCase() {
        projectViewModel.fetchProjects()

        verify(getProjects, times(2)).execute(any(), eq(null))
    }

    @Test
    fun fetchProjectsReturnsSuccess() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        projectViewModel.fetchProjects()

        verify(getProjects, times(2)).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(ResourceState.SUCCESS,
                projectViewModel.getProjects().value?.state)
    }

    @Test
    fun fetchProjectsReturnsData() {
        val projects = ProjectFactory.makeProjectList(2)
        val projectViews = ProjectFactory.makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        projectViewModel.fetchProjects()

        verify(getProjects, times(2)).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        assertEquals(projectViews,
                projectViewModel.getProjects().value?.data)
    }

    @Test
    fun fetchProjectsReturnsError() {
        projectViewModel.fetchProjects()

        verify(getProjects, times(2)).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(ResourceState.ERROR,
                projectViewModel.getProjects().value?.state)
    }

    @Test
    fun fetchProjectsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        projectViewModel.fetchProjects()

        verify(getProjects, times(2)).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage,
                projectViewModel.getProjects().value?.message)
    }

    private fun stubProjectMapperMapToView(projectView: ProjectView,
                                           project: Project) {
        whenever(mapper.mapToView(project))
                .thenReturn(projectView)
    }
}
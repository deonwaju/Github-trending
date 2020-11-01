package com.deonolarewaju.remote

import com.deonolarewaju.ProjectRemoteImpl
import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.mapper.ProjectResponseModelMapper
import com.deonolarewaju.remote.model.ProjectModel
import com.deonolarewaju.remote.model.ProjectsResponseModel
import com.deonolarewaju.remote.service.GithubTrendingService
import com.deonolarewaju.remote.test.factory.ProjectDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectRemoteImplTest {

    private val mapper = mock<ProjectResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectRemoteImpl(service, mapper)

    @Test
    fun getProjectCompletes() {
        stubGithubTrendingServicesSearchRepos(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectResponseModelMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())

        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectCallServer() {
        stubGithubTrendingServicesSearchRepos(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectResponseModelMapperMapFromModel(
            any(),
            ProjectDataFactory.makeProjectEntity()
        )
        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectReturnsData() {
        val response = ProjectDataFactory.makeProjectsResponse()
        stubGithubTrendingServicesSearchRepos(Observable.just(response))
        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach{
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubProjectResponseModelMapperMapFromModel(it, entity)
        }

        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)

    }

    @Test
    fun getProjectCallServerWithCorrectParameters() {
        stubGithubTrendingServicesSearchRepos(
            Observable.just(
                ProjectDataFactory.makeProjectsResponse()
            )
        )
        stubProjectResponseModelMapperMapFromModel(
            any(),
            ProjectDataFactory.makeProjectEntity()
        )
        remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }


    private fun stubGithubTrendingServicesSearchRepos(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubProjectResponseModelMapperMapFromModel(
        model: ProjectModel,
        entity: ProjectEntity
    ) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }

}
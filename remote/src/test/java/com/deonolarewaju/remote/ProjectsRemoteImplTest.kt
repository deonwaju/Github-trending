package com.deonolarewaju.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.remote.mapper.ProjectsResponseModelMapper
import com.deonolarewaju.remote.model.ProjectModel
import com.deonolarewaju.remote.model.ProjectsResponseModel
import com.deonolarewaju.remote.test.factory.ProjectDataFactory
import com.deonolarewaju.remote.service.GithubTrendingService

@RunWith(JUnit4::class)
class ProjectsRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingService>()
    private val remote = ProjectsRemoteImpl(service, mapper)

    private fun stubServiceSearchRepositories(flowable: Flowable<ProjectsResponseModel>){
        whenever(service.searchRepositories(any(), any(), any()))
                .thenReturn(flowable)
    }

    private fun stubMapperMapFromModel(model: ProjectModel, entity: ProjectEntity){
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)
    }

    @Test
    fun getProjectsCompletes() {
        stubServiceSearchRepositories(Flowable.just(ProjectDataFactory.makeProjectsResponse()))
        stubMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        val testObserver = remote.getProjects().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsCallsServer(){
        stubServiceSearchRepositories(Flowable.just(ProjectDataFactory.makeProjectsResponse()))
        stubMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        remote.getProjects().test()
        verify(service).searchRepositories(any(), any(), any())
    }

    @Test
    fun getProjectsReturnsCorrectData(){
        val response = ProjectDataFactory.makeProjectsResponse()
        stubServiceSearchRepositories(Flowable.just(response))

        val entities = mutableListOf<ProjectEntity>()
        response.items.forEach {
            val entity = ProjectDataFactory.makeProjectEntity()
            entities.add(entity)
            stubMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getProjects().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getProjectsCallsServerWithCorrectParameters(){
        stubServiceSearchRepositories(Flowable.just(ProjectDataFactory.makeProjectsResponse()))
        stubMapperMapFromModel(any(), ProjectDataFactory.makeProjectEntity())
        val testObserver = remote.getProjects().test()
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }
}
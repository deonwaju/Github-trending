package com.deonolarewaju.remote

import io.reactivex.Flowable
import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectsRemote
import com.deonolarewaju.remote.mapper.ProjectsResponseModelMapper
import com.deonolarewaju.remote.service.GithubTrendingService
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
        private val service: GithubTrendingService,
        private val mapper: ProjectsResponseModelMapper
): ProjectsRemote {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
                .map {
                    it.items.map{ mapper.mapFromModel(it) }
                }
    }
}
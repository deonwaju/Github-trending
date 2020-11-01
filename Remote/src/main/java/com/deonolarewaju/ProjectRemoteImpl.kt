package com.deonolarewaju

import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectRemote
import com.deonolarewaju.mapper.ProjectResponseModelMapper
import com.deonolarewaju.remote.service.GithubTrendingService
import io.reactivex.Observable
import javax.inject.Inject

class ProjectRemoteImpl @Inject constructor(
    private val service: GithubTrendingService,
    private val mapper: ProjectResponseModelMapper
) : ProjectRemote {

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map {
                it.items.map {
                    mapper.mapFromModel(it)
                }
            }
    }

}
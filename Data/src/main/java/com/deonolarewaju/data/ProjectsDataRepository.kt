package com.deonolarewaju.data

import com.deonolarewaju.data.mapper.ProjectMapper
import com.deonolarewaju.data.repository.ProjectsCache
import com.deonolarewaju.data.store.ProjectDataStoreFactory
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.domain.repository.ProjectRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val factory: ProjectDataStoreFactory
) : ProjectRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(cache.areProjectsCached().toObservable(),
            cache.isProjectCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            }).flatMap { factory.getDataStore(it.first, it.second).getProjects() }
            .flatMap { projects ->
                factory.getCacheDataStore().saveProjects(projects)
                    .andThen(Observable.just(projects))
            }
            .map { it.map { mapper.mapFromEntity(it) } }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unBookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookMarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects()
            .map { it.map { mapper.mapFromEntity(it) } }
    }
}
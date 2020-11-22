package com.deonolarewaju.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import com.deonolarewaju.data.mapper.ProjectMapper
import com.deonolarewaju.data.repository.ProjectsCache
import com.deonolarewaju.data.store.ProjectsDataStoreFactory
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsDataRepository @Inject constructor(
        private val mapper: ProjectMapper,
        private val cache: ProjectsCache,
        private val factory: ProjectsDataStoreFactory
) : ProjectsRepository {

    override fun getProjects(): Observable<List<Project>> {
        return Observable.zip(
                cache.areProjectsCached().toObservable(),
                cache.isProjectsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> {
                    areCached, isExpired -> Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second)
                            .getProjects().toObservable()
                            .distinctUntilChanged()
                }
                .flatMap { projects ->
                    factory.getCacheDataStore()
                            .saveProjects(projects)
                            .andThen(Observable.just(projects))
                }
                .map {
                    it.map {
                        mapper.mapFromEntity(it)
                    }
                }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    override fun getBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().getBookmarkedProjects().toObservable()
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }
}
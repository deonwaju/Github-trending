package com.deonolarewaju.cache

import com.deonolarewaju.cache.db.ProjectDataBase
import com.deonolarewaju.cache.mapper.CachedProjectMapper
import com.deonolarewaju.cache.model.Config
import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import kotlin.math.exp

class ProjectCacheImpl @Inject constructor(
    private val projectDataBase: ProjectDataBase,
    private val mapper: CachedProjectMapper
) : ProjectsCache {
    override fun clearProjects(): Completable {
        return Completable.defer {
            projectDataBase.cachedProjectsDao().deleteProjects()
            Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectDataBase.cachedProjectsDao().insertProjects(
                projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectDataBase.cachedProjectsDao().getProjects()
            .toObservable()
            .map {
                it.map { mapper.mapFromCached(it) }
            }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectDataBase.cachedProjectsDao().getBookMarkedProjects()
            .toObservable()
            .map {
                it.map { mapper.mapFromCached(it) }
            }
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectDataBase.cachedProjectsDao().setBookMarkedStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return Completable.defer {
            projectDataBase.cachedProjectsDao().setBookMarkedStatus(false, projectId)
            Completable.complete()
        }

    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectDataBase.cachedProjectsDao().getProjects().isEmpty
            .map { !it }
    }

    override fun setLastCachedTime(lastCache: Long): Completable {
        return Completable.defer {
            projectDataBase.configDao().insertConfig(Config(lastCachedTime = lastCache))
            Completable.complete()
        }
    }

    override fun isProjectCacheExpired(): Flowable<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 100).toLong()
        return projectDataBase.configDao().getConfig()
            .onErrorReturn { Config(lastCachedTime = 0) }
            .map {
                currentTime - it.lastCachedTime > expirationTime
            }
    }
}
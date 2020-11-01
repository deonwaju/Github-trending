package com.deonolarewaju.data.store

import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectDataStore
import com.deonolarewaju.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectCacheDataStore @Inject constructor(
    private val projectsCache: ProjectsCache
) : ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastCachedTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsBookmarked(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.setProjectAsNotBookmarked(projectId)
    }

}
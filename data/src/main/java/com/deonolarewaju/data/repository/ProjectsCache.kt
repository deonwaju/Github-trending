package com.deonolarewaju.data.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.deonolarewaju.data.model.ProjectEntity

interface ProjectsCache {

    fun clearProjects(): Completable

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun getProjects(): Flowable<List<ProjectEntity>>

    fun getBookmarkedProjects(): Flowable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable

    fun areProjectsCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isProjectsCacheExpired(): Single<Boolean>
}
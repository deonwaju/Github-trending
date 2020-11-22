package com.deonolarewaju.data.store

import io.reactivex.Completable
import io.reactivex.Flowable
import com.deonolarewaju.data.model.ProjectEntity

interface ProjectsDataStore {

    fun getProjects(): Flowable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Flowable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable
}
package com.deonolarewaju.data.repository

import com.deonolarewaju.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectDataStore {

    fun getProjects(): Observable<List<ProjectEntity>>

    fun saveProjects(projects: List<ProjectEntity>): Completable

    fun clearProjects(): Completable

    fun getBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun setProjectAsBookmarked(projectId: String): Completable

    fun setProjectAsNotBookmarked(projectId: String): Completable


}
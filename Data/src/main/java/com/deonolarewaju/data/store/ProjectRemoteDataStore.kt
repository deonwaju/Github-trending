package com.deonolarewaju.data.store

import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectDataStore
import com.deonolarewaju.data.repository.ProjectRemote
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ProjectRemoteDataStore @Inject constructor(private val projectRemote: ProjectRemote): ProjectDataStore {
    override fun getProjects(): Observable<List<ProjectEntity>> {
        return projectRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported here")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported here")
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Getting bookmarked projects not supported here")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting projects as bookmarked not supported here")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting project as not bookmarked not supported here")
    }
}
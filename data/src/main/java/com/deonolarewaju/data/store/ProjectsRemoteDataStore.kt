package com.deonolarewaju.data.store

import io.reactivex.Completable
import io.reactivex.Flowable
import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.data.repository.ProjectsRemote
import javax.inject.Inject

class ProjectsRemoteDataStore @Inject constructor(
        private val projectsRemote: ProjectsRemote
) : ProjectsDataStore {

    override fun getProjects(): Flowable<List<ProjectEntity>> {
        return projectsRemote.getProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported in the remote")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported in the remote")
    }

    override fun getBookmarkedProjects(): Flowable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Bookmarking projects isn't supported in the remote")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting projects as bookmarked isn't supported " +
                "in the remote")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Setting projects as not bookmarked isn't supported " +
                "in the remote")
    }
}
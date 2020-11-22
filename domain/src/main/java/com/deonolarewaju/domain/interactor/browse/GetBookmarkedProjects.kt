package com.deonolarewaju.domain.interactor.browse

import io.reactivex.Observable
import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.interactor.ObservableUseCase
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.domain.repository.ProjectsRepository
import javax.inject.Inject

class GetBookmarkedProjects @Inject constructor(
        private val projectsRepository: ProjectsRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun createObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }
}
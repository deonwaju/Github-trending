package com.deonolarewaju.domain.interactor.bookmarked

import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.interactor.ObservableUseCase
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBookmarkedProjects @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectRepository.getBookMarkedProjects()
    }
}
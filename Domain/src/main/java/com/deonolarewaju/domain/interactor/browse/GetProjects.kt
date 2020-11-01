package com.deonolarewaju.domain.interactor.browse

import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.interactor.ObservableUseCase
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.domain.repository.ProjectRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Project>, Nothing?>(postExecutionThread) {
    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }


}
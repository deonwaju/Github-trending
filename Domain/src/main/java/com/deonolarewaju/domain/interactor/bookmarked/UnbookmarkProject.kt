package com.deonolarewaju.domain.interactor.bookmarked

import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.interactor.CompletableUseCase
import com.deonolarewaju.domain.repository.ProjectRepository
import io.reactivex.Completable
import javax.inject.Inject

open class UnbookmarkProject @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<UnbookmarkProject.Params>(postExecutionThread) {
    public override fun buildUseCaseCompletable(params: UnbookmarkProject.Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return projectRepository.unBookmarkProject(params.projectId)
    }

    data class Params(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }
}
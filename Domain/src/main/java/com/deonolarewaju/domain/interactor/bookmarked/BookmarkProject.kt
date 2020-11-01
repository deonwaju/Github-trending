package com.deonolarewaju.domain.interactor.bookmarked

import com.deonolarewaju.domain.executor.PostExecutionThread
import com.deonolarewaju.domain.interactor.CompletableUseCase
import com.deonolarewaju.domain.repository.ProjectRepository
import io.reactivex.Completable
import javax.inject.Inject

open class BookmarkProject @Inject constructor(
    private val projectRepository: ProjectRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<BookmarkProject.Params>(postExecutionThread) {
    public override fun buildUseCaseCompletable(params: BookmarkProject.Params?): Completable {
        if (params == null) throw IllegalAccessException("Params can't be null")
        return projectRepository.bookmarkProject(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object{
            fun forProject(projectId: String) : Params {
                return Params(projectId)
            }
        }
    }
}
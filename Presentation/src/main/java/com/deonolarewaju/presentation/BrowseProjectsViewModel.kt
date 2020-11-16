package com.deonolarewaju.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.deonolarewaju.domain.interactor.bookmarked.BookmarkProject
import com.deonolarewaju.domain.interactor.bookmarked.UnbookmarkProject
import com.deonolarewaju.domain.interactor.browse.GetProjects
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.presentation.mapper.ProjectViewMapper
import com.deonolarewaju.presentation.model.ProjectView
import com.deonolarewaju.presentation.state.Resource
import com.deonolarewaju.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

open class BrowseProjectsViewModel @Inject internal constructor(
    private val getProjects: GetProjects?,
    private val bookmarkProject: BookmarkProject,
    private val unBookmarkProject: UnbookmarkProject,
    private val mapper: ProjectViewMapper
) : ViewModel() {
    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    init {
        fetchProjects()
    }

    override fun onCleared() {
        getProjects?.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData
    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getProjects?.execute(ProjectSubscriber())
    }

    fun bookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return bookmarkProject.execute(
            BookmarkProjectSubscriber(),
            BookmarkProject.Params.forProject(projectId)
        )
    }

    fun unBookmarkProject(projectId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return unBookmarkProject.execute(
            BookmarkProjectSubscriber(),
            UnbookmarkProject.Params.forProject(projectId)
        )
    }


    inner class ProjectSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {
            TODO("Not yet implemented")
        }

        override fun onNext(t: List<Project>) {
            liveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map {
                        mapper.mapToView(it)
                    }, null
                )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class BookmarkProjectSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
            return
        }

        override fun onError(e: Throwable) {
            liveData.postValue(
                Resource(
                    ResourceState.ERROR,
                    liveData.value?.data,
                    e.localizedMessage
                )
            )
        }

    }
}
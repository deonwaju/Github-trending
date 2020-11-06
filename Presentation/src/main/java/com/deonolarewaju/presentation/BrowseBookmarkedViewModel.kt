package com.deonolarewaju.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.deonolarewaju.domain.interactor.bookmarked.GetBookmarkedProjects
import com.deonolarewaju.domain.model.Project
import com.deonolarewaju.presentation.mapper.ProjectViewMapper
import com.deonolarewaju.presentation.model.ProjectView
import com.deonolarewaju.presentation.state.Resource
import com.deonolarewaju.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseBookmarkedViewModel @Inject constructor(
    private val getBookmarkedProjects: GetBookmarkedProjects,
    private val mapper: ProjectViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    override fun onCleared() {
        getBookmarkedProjects.dispose()
        super.onCleared()
    }

    fun getProjects(): LiveData<Resource<List<ProjectView>>> {
        return liveData

    }

    fun fetchProjects() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBookmarkedProjects.execute(ProjectSubscriber())
    }

    inner class ProjectSubscriber : DisposableObserver<List<Project>>() {
        override fun onComplete() {
            TODO("Not yet implemented")
        }

        override fun onNext(t: List<Project>) {
            liveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) },
                    null
                )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

}
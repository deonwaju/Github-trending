package com.deonolarewaju.data.repository

import io.reactivex.Flowable
import com.deonolarewaju.data.model.ProjectEntity

interface ProjectsRemote {

    fun getProjects(): Flowable<List<ProjectEntity>>
}
package com.deonolarewaju.data.repository

import com.deonolarewaju.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectRemote {

    fun getProjects(): Observable<List<ProjectEntity>>
}
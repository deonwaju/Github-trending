package com.deonolarewaju.domain.repository

import com.deonolarewaju.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import java.util.*
import java.util.concurrent.CompletableFuture

interface ProjectRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unBookmarkProject(projectId: String): Completable

    fun getBookMarkedProjects(): Observable<List<Project>>
}
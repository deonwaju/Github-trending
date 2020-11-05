package com.deonolarewaju.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.deonolarewaju.cache.db.ProjectConstants.DELETE_PROJECTS
import com.deonolarewaju.cache.db.ProjectConstants.QUERY_BOOKMARKED_PROJECTS
import com.deonolarewaju.cache.db.ProjectConstants.QUERY_PROJECTS
import com.deonolarewaju.cache.db.ProjectConstants.QUERY_UPDATE_BOOKMARK_STATUS
import com.deonolarewaju.cache.model.CachedProject
import io.reactivex.Flowable

@Dao
abstract class CachedProjectsDao {

    @Query(QUERY_PROJECTS)
    abstract fun getProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProjects(project: List<CachedProject>)

    @Query(DELETE_PROJECTS)
    abstract fun deleteProjects()

    @Query(QUERY_BOOKMARKED_PROJECTS)
    abstract fun getBookMarkedProjects(): Flowable<List<CachedProject>>

    @Query(QUERY_UPDATE_BOOKMARK_STATUS)
    abstract fun setBookMarkedStatus(isBookmarked: Boolean, projectId: String)

}
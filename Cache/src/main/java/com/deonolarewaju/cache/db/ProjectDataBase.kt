package com.deonolarewaju.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.deonolarewaju.cache.dao.CachedProjectsDao
import com.deonolarewaju.cache.dao.ConfigDao
import com.deonolarewaju.cache.model.CachedProject
import com.deonolarewaju.cache.model.Config
import javax.inject.Inject

@Database(
    entities = arrayOf(CachedProject::class, Config::class),
    version = ProjectConstants.DATABASE_VERSION
)
abstract class ProjectDataBase @Inject constructor() : RoomDatabase() {

    abstract fun cachedProjectsDao(): CachedProjectsDao

    abstract fun configDao(): ConfigDao

    private var INSTANCE: ProjectDataBase? = null
    private var lock = Any()

    fun getInstance(context: Context): ProjectDataBase {
        if (INSTANCE == null) {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ProjectDataBase::class.java, "projects.db"
                    )
                        .build()
                }
                return INSTANCE as ProjectDataBase
            }
        }
        return INSTANCE as ProjectDataBase
    }
}
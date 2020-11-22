package com.deonolarewaju.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single
import com.deonolarewaju.cache.db.ConfigConstants.QUERY_CONFIG
import com.deonolarewaju.cache.model.Config

@Dao
abstract class ConfigDao {

    @Query(QUERY_CONFIG)
    abstract fun getConfig(): Single<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)
}
package com.deonolarewaju.cache.mapper

import com.deonolarewaju.cache.model.CachedProject
import com.deonolarewaju.data.model.ProjectEntity

class CachedProjectMapper : CacheMapper<CachedProject, ProjectEntity> {
    override fun mapFromCached(type: CachedProject): ProjectEntity {
        return ProjectEntity(
            type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookMarked
        )
    }

    override fun mapToCached(type: ProjectEntity): CachedProject {
        return CachedProject(
            type.id, type.name, type.fullName, type.starCount,
            type.dateCreated, type.ownerName, type.ownerAvatar,
            type.isBookmarked
        )
    }
}
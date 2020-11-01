package com.deonolarewaju.mapper

import com.deonolarewaju.data.model.ProjectEntity
import com.deonolarewaju.remote.model.ProjectModel

open class ProjectResponseModelMapper : ModelMapper<ProjectModel, ProjectEntity> {

    override fun mapFromModel(model: ProjectModel): ProjectEntity {
        return ProjectEntity(model.id, model.name, model.fullName, model.starCount.toString(),
        model.dateCreated, model.owner.ownerName, model.owner.ownerAvatar, isBookmarked = false)
    }


}
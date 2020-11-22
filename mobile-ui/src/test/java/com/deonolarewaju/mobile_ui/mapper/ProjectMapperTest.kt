package com.deonolarewaju.mobile_ui.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import com.deonolarewaju.mobile_ui.test.factory.ProjectFactory

class ProjectMapperTest {

    private val projectMapper = ProjectViewMapper()

    @Test
    fun mapToViewMapsData() {
        val project = ProjectFactory.makeProjectView()
        val projectForUi = projectMapper.mapToView(project)

        assertEquals(project.id, projectForUi.id)
        assertEquals(project.name, projectForUi.name)
        assertEquals(project.fullName, projectForUi.fullName)
        assertEquals(project.starCount, projectForUi.starCount)
        assertEquals(project.dateCreated, projectForUi.dateCreated)
        assertEquals(project.ownerName, projectForUi.ownerName)
        assertEquals(project.ownerAvatar, projectForUi.ownerAvatar)
        assertEquals(project.isBookmarked, projectForUi.isBookmarked)
    }
}
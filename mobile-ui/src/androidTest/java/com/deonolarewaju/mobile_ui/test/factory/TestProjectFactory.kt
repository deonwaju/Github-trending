package com.deonolarewaju.mobile_ui.test.factory

import com.deonolarewaju.presentation.model.ProjectView

object TestProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomUuid(),
                TestDataFactory.randomUuid(), TestDataFactory.randomUuid(), TestDataFactory.randomBoolean())
    }
}
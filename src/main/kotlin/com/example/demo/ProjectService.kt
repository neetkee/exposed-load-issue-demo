package com.example.demo

import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class ProjectService {
    fun addProject(request: ProjectAddRequest) = transaction {
        val project = Project.new { name = request.name }
        val attributesById = request.attributes
            .map { it.id }
            .let { Attribute.find { AttributeTable.id.inList(it) } }
            .associateBy { it.id.value }
        request.attributes.forEach { requestAttribute ->
            val attribute = attributesById[requestAttribute.id]!!
            ProjectAttribute.new {
                this.attribute = attribute
                this.project = project
                this.value = requestAttribute.value
            }
        }
//        project.flush()
        project.toView()
    }

    private fun Project.toView(): ProjectView {
        load(Project::attributes, ProjectAttribute::attribute) // remove for tests to pass or flush object before
        return ProjectView(
            id = this.id.value,
            attributes = this.attributes.map {
                ProjectView.Attribute(it.id.value, it.attribute.name, it.value)
            }
        )
    }
}
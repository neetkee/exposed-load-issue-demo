package com.example.demo

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ProjectTable : IntIdTable("projects") {
    val name = text("name")
}

class Project(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Project>(ProjectTable)

    var name by ProjectTable.name
    val attributes by ProjectAttribute referrersOn ProjectAttributeTable.projectId
}

object ProjectAttributeTable : IntIdTable("project_attributes") {
    val attributeTd = reference("attribute_id", AttributeTable)
    val projectId = reference("project_id", ProjectTable)
    val value = text("value")
}

class ProjectAttribute(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProjectAttribute>(ProjectAttributeTable)

    var attribute by Attribute referencedOn ProjectAttributeTable.attributeTd
    var project by Project referencedOn ProjectAttributeTable.projectId
    var value by ProjectAttributeTable.value
}
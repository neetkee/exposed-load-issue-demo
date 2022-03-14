package com.example.demo

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object AttributeTable : IntIdTable("attributes") {
    val name = text("name")
}

class Attribute(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Attribute>(AttributeTable)

    var name by AttributeTable.name
}
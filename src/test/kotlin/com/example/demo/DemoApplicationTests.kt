package com.example.demo

import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    lateinit var projectService: ProjectService

    @Test
    fun `test with attributes`() {
        val request = ProjectAddRequest("test", emptyList())
        projectService.addProject(request)
    }

    @Test
    fun `test without attributes`() {
        val attributeId = transaction {
            Attribute.new { name = "test" }.id.value
        }

        val request = ProjectAddRequest(
            name = "test",
            attributes = listOf(ProjectAddRequest.Attribute(attributeId, "value"))
        )
        projectService.addProject(request)
    }
}

package com.example.demo

class ProjectAddRequest(
    val name: String,
    val attributes: List<Attribute>
) {
    class Attribute(val id: Int, val value: String)
}

class ProjectView(
    val id: Int,
    val attributes: List<Attribute>
) {
    class Attribute(val id: Int, val name: String, val value: String)
}
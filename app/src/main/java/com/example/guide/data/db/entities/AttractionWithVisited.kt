package com.example.guide.data.db.entities

data class AttractionWithVisited(
    val id: Int,
    val title: String,
    val description: String,
    val location: String,
    val type: String,
    val visited: Int
)
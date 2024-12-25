package com.example.guide.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visited")
data class Visited(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val attractionId: Int,
)

package com.example.guide.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.guide.data.db.dao.AttractionDao
import com.example.guide.data.db.dao.UserNoteDao
import com.example.guide.data.db.dao.VisitedDao
import com.example.guide.data.db.entities.Attraction
import com.example.guide.data.db.entities.UserNote
import com.example.guide.data.db.entities.Visited

@Database(entities = [Attraction::class, UserNote::class, Visited::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun attractionDao(): AttractionDao
    abstract fun userNoteDao(): UserNoteDao
    abstract fun visitedDao(): VisitedDao
}

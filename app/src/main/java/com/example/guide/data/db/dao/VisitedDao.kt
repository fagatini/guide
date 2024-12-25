package com.example.guide.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guide.data.db.entities.Visited
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVisited(visited: Visited)

    @Query("SELECT EXISTS (SELECT * FROM visited WHERE attractionId = :attractionId AND userId = :userId)")
    fun getThisAttractionVisited(attractionId: Int, userId: Int): Flow<Boolean>
}

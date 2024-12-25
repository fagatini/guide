package com.example.guide.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guide.data.db.entities.Attraction
import com.example.guide.data.db.entities.AttractionWithVisited
import com.example.guide.data.db.entities.Visited
import kotlinx.coroutines.flow.Flow

@Dao
interface AttractionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttraction(attraction: Attraction)

    @Query("SELECT a.id, a.description, a.location, a.title, a.type, CASE WHEN EXISTS (SELECT 1 FROM visited v WHERE v.userId = :userId AND v.attractionId = a.id) THEN 1 ELSE 0 END AS 'visited' FROM attractions a")
    fun getAllAttractionsWithVisited(userId: Int): Flow<List<AttractionWithVisited>>

    @Query("SELECT * FROM attractions WHERE id = :attractionId")
    fun getAttractionById(attractionId: Int): Flow<Attraction?>
}

package com.example.guide.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.guide.data.db.entities.UserNote
import kotlinx.coroutines.flow.Flow

@Dao
interface UserNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserNote(note: UserNote)

    @Query("SELECT * FROM user_notes WHERE attractionId = :attractionId")
    fun getNotesForAttraction(attractionId: Int): Flow<List<UserNote>>
}

package com.example.guide.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guide.data.db.dao.UserNoteDao
import com.example.guide.data.db.entities.UserNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val dao: UserNoteDao,
) : ViewModel() {
    fun getNotesForAttraction(attractionId: Int): Flow<List<UserNote>> {
        return dao.getNotesForAttraction(attractionId)
    }

    fun addNote(note: UserNote) {
        viewModelScope.launch {
            dao.insertUserNote(note)
        }
    }
}
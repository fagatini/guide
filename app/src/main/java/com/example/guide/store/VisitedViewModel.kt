package com.example.guide.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guide.data.db.dao.VisitedDao
import com.example.guide.data.db.entities.Visited
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VisitedViewModel(
    private val dao: VisitedDao,
) : ViewModel() {
    fun addVisited(visited: Visited) {
        viewModelScope.launch {
            dao.addVisited(visited)
        }
    }

    fun getThisAttractionVisited(attractionId: Int, userId: Int): Flow<Boolean> {
        return dao.getThisAttractionVisited(attractionId, userId)
    }
}

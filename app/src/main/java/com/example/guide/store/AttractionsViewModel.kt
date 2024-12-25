package com.example.guide.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guide.data.db.dao.AttractionDao
import com.example.guide.data.db.entities.Attraction
import com.example.guide.data.db.entities.AttractionWithVisited
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AttractionsViewModel(private val dao: AttractionDao) : ViewModel() {
    fun getAllAttractionsWithVisited(userId: Int): Flow<List<AttractionWithVisited>> {
        return dao.getAllAttractionsWithVisited(userId)
    }

    fun getAttractionById(attractionId: Int): Flow<Attraction?> {
        return dao.getAttractionById(attractionId)
    }

    fun addAttraction(attraction: Attraction) {
        viewModelScope.launch {
            dao.insertAttraction(attraction)
        }
    }
}

package com.example.guide.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.guide.data.db.entities.Attraction
import com.example.guide.data.db.entities.AttractionWithVisited

@Composable
fun AttractionItem(
    attraction: AttractionWithVisited,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = attraction.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Местоположение: ${attraction.location}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Тип: ${attraction.type}", style = MaterialTheme.typography.bodyMedium)
            Text(text = if (attraction.visited == 1) "Посещено" else "Не посещено", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

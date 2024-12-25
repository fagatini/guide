package com.example.guide.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.guide.data.db.entities.UserNote

@Composable
fun NoteItem(
    note: UserNote,
) {
    Card(
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = " ${note.note}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

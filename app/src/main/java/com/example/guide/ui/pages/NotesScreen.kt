package com.example.guide.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guide.data.db.entities.UserNote
import com.example.guide.store.AttractionsViewModel
import com.example.guide.store.NotesViewModel
import com.example.guide.store.VisitedViewModel
import com.example.guide.data.db.entities.Visited
import com.example.guide.ui.components.NoteItem
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    userId: Int,
    attractionId: Int,
    navController: NavController,
) {
    val attractionModel: AttractionsViewModel = getViewModel()
    val notesModel: NotesViewModel = getViewModel()
    val visitedModel: VisitedViewModel = getViewModel()

    val attraction by attractionModel.getAttractionById(attractionId).collectAsState(initial = null)
    val userNotes = notesModel.getNotesForAttraction(attractionId)
        .collectAsState(initial = emptyList())

    val isVisited = visitedModel.getThisAttractionVisited(attractionId, userId)
        .collectAsState(initial = false)

    var newNoteText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Место: ${attraction?.title ?: "Загрузка..."}")},
                navigationIcon = {
                IconButton(onClick = {navController.navigate("attractions")}) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                }
            })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = "Описание: ${attraction?.description ?: "Загрузка..."}",
                style = MaterialTheme.typography.bodyLarge,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (isVisited.value) {
                    Text("Уже посещено", color = MaterialTheme.colorScheme.primary)
                } else {
                    Button(
                        onClick = {
                            visitedModel.addVisited(
                                Visited(
                                    userId = userId,
                                    attractionId = attractionId
                                )
                            )
                        },
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text("Я посетил это место")
                    }
                }
            }


            OutlinedTextField(
                value = newNoteText,
                onValueChange = { newNoteText = it },
                label = { Text("Введите заметку") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (newNoteText.isNotBlank()) {
                        val newNote = UserNote(
                            userId = userId,
                            attractionId = attractionId,
                            note = newNoteText,
                        )
                        notesModel.addNote(newNote)
                        newNoteText = ""
                    }
                },
            ) {
                Text("Отправить")
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(userNotes.value.size) { index ->
                    NoteItem(
                        note = userNotes.value[index],
                    )
                }
            }
        }
    }
}

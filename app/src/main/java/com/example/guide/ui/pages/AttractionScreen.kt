package com.example.guide.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guide.store.AttractionsViewModel
import com.example.guide.ui.components.AttractionItem
import org.koin.androidx.compose.getViewModel

@Composable
fun AttractionScreen(
    navController: NavController,
    userId: Int
) {
    val viewModel: AttractionsViewModel = getViewModel()
    val attractions by viewModel.getAllAttractionsWithVisited(userId).collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Список достопримечательностей",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            items(attractions.size) { index ->
                AttractionItem(
                    attraction = attractions[index],
                    onClick = {
                        navController.navigate("notes/${attractions[index].id}")
                    }
                )
            }
        }

        Button(
            onClick = {
                navController.navigate("add_attraction")
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Добавить достопримечательность")
        }
    }
}

package com.example.guide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.guide.store.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.guide.store.AttractionsViewModel
import com.example.guide.store.NotesViewModel
import com.example.guide.store.VisitedViewModel
import com.example.guide.ui.pages.AttractionScreen
import com.example.guide.ui.pages.NotesScreen
import com.example.guide.ui.pages.AddAttractionScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val attractionModel: AttractionsViewModel by viewModel()
    private val notesModel: VisitedViewModel by viewModel()
    private val visitedModel: NotesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(applicationContext)
            modules(appModule)
        }

        setContent {
            MaterialTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val userId = 1

    NavHost(
        navController,
        startDestination = "attractions"
    ) {
        composable("attractions") {
            AttractionScreen(navController, userId)
        }

        composable("add_attraction") {
            AddAttractionScreen(navController)
        }

        composable(
            "notes/{attractionId}",
            arguments = listOf(navArgument("attractionId") { type = NavType.IntType })
        ) { backStackEntry ->
            val attractionId = backStackEntry.arguments?.getInt("attractionId") ?: 0
            NotesScreen(
                userId,
                attractionId,
                navController,
            )
        }
    }
}

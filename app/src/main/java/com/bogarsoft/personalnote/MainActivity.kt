package com.bogarsoft.personalnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bogarsoft.personalnote.ui.screens.AddNoteScreen.AddNoteScreen
import com.bogarsoft.personalnote.ui.screens.HomeScreen.HomeScreen
import com.bogarsoft.personalnote.ui.theme.PersonalNoteTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home_screen") {
                        composable("home_screen") {
                            HomeScreen(navController = navController)
                        }
                        composable("add_note_screen") {
                            AddNoteScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


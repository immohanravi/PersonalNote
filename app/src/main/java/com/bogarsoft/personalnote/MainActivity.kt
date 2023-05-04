package com.bogarsoft.personalnote

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.bogarsoft.personalnote.data.NoteRepository
import com.bogarsoft.personalnote.database.DBHandler
import com.bogarsoft.personalnote.models.Note
import com.bogarsoft.personalnote.ui.screens.AddNoteScreen.AddNoteScreen
import com.bogarsoft.personalnote.ui.screens.HomeScreen.HomeScreen
import com.bogarsoft.personalnote.ui.screens.HomeViewModel
import com.bogarsoft.personalnote.ui.theme.PersonalNoteTheme
import com.bogarsoft.personalnote.utils.findActivity
import org.dizitart.no2.objects.ObjectRepository


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
                    val openDialog = remember { mutableStateOf(false)  }
                    val navController = rememberNavController()
                    val db = DBHandler.getDBInstance(applicationContext)
                    val repo = db.getRepository(Note::class.java)
                    val respository = NoteRepository(repo)
                    val viewModel = HomeViewModel(respository)
                    NavHost(navController = navController, startDestination = "home_screen") {
                        composable("home_screen"){
                            /*val activity = LocalContext.current.findActivity()
                            activity?.intent?.let { intent ->
                                if (intent.action == Intent.ACTION_SEND) {
                                    intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                                        Log.d(TAG, "onCreate: $text")
                                    }

                                }
                            }*/
                            HomeScreen(navController = navController,viewModel = viewModel)
                        }
                        composable("add_note_screen") {
                            AddNoteScreen(navController = navController) { note ->
                                viewModel.addNote(note)
                                navController.navigate("home_screen")
                            }
                        }
                        composable("receive_note_screen",deepLinks = listOf( navDeepLink {
                            action = Intent.ACTION_SEND
                            mimeType = "text/*"
                        }
                        )) {
                            val activity = LocalContext.current.findActivity()
                            activity?.intent?.let { intent ->
                                if (intent.action == Intent.ACTION_SEND) {
                                    intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                                        openDialog.value = true
                                    }
                                }
                            }

                            if (openDialog.value) {

                                AlertDialog(
                                    onDismissRequest = {
                                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                                        // button. If you want to disable that functionality, simply use an empty
                                        // onCloseRequest.
                                        openDialog.value = false
                                    },
                                    title = {
                                        Text(text = "Dialog Title")
                                    },
                                    text = {
                                        Text("Here is a text ")
                                    },
                                    confirmButton = {
                                        Button(

                                            onClick = {
                                                openDialog.value = false
                                            }) {
                                            Text("This is the Confirm Button")
                                        }
                                    },
                                    dismissButton = {
                                        Button(

                                            onClick = {
                                                openDialog.value = false
                                            }) {
                                            Text("This is the dismiss Button")
                                        }
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}


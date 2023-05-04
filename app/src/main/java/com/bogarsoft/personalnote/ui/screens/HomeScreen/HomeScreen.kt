package com.bogarsoft.personalnote.ui.screens.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bogarsoft.personalnote.R
import com.bogarsoft.personalnote.data.SampleData
import com.bogarsoft.personalnote.ui.components.NoteComponent
import com.bogarsoft.personalnote.ui.screens.HomeViewModel
import com.bogarsoft.personalnote.utils.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val state = viewModel.data.value
    viewModel.getNotes()
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddEditNoteScreen.route)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        topBar = {
            //topbar with back button
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                    )
                },
            )


        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),

                    modifier = Modifier.padding(4.dp)
                ){
                    items(state.size){
                        NoteComponent(note = state[it], onDelete = {
                            viewModel.delete(it)
                        })
                    }
                }

                //AlertDialogSample()
            }
        }
    )
}

@Composable
fun AlertDialogSample() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false)  }

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Click me")
            }


        }

    }
}

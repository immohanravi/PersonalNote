package com.bogarsoft.noteify.ui.screens.addNoteScreen

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bogarsoft.noteify.models.Note
import com.bogarsoft.noteify.ui.components.TransparentHintTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.Date
import java.util.UUID


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navController: NavController,
    note: Note? = null,
    onClick: (Note) ->Unit,
) {

    var title by remember { mutableStateOf("") }
    var content by remember {
        mutableStateOf("")
    }

    var hideTitleHint by remember {
        mutableStateOf(true)
    }
    var hideContentHint by remember {
        mutableStateOf(true)
    }

    if (note != null) {
        title = note.title
        content = note.content
    }

    Scaffold(
        topBar = {
            //topbar with back button
            TopAppBar(
                title = {
                    Text(
                        text = "Add Note",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    )
                },
                navigationIcon = {
                   IconButton(onClick = {navController.navigateUp()}) {
                       Icon(
                           imageVector = Icons.Default.ArrowBack,
                           contentDescription = "Back",
                       )

                   }
                },

                )


        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                    if (title.isNotEmpty() && content.isNotEmpty()) {
                        onClick(
                            Note(
                                id = UUID.randomUUID().toString(),
                                title = title,
                                content = content,
                                date = Date()
                            )
                        )


                    }else {

                        toast(navController.context, "Please fill all the fields")

                    }

                },
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
            }
        },

        content = {paddingValues->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)) {

                    TransparentHintTextField(
                        text = title,
                        hint = "Note Title",
                        onValueChange = {
                            title = it
                            hideTitleHint = title.isEmpty()
                        },
                        onFocusChange = {

                        },
                        isHintVisible = hideTitleHint,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TransparentHintTextField(
                        text = content,
                        hint = "Note Content",
                        onValueChange = {
                            content = it
                            hideContentHint = content.isEmpty()
                        },
                        onFocusChange = {

                        },
                        isHintVisible = hideContentHint,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxHeight()
                    )

            }
        }
    )
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}


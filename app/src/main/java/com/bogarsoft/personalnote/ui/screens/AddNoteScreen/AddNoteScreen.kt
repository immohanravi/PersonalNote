package com.bogarsoft.personalnote.ui.screens.AddNoteScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bogarsoft.personalnote.ui.components.TransparentHintTextField


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navController: NavController
) {


    Scaffold(
        topBar = {
            //topbar with back button
            TopAppBar(
                title = {
                    Text(
                        text = "Add Note",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(start = 8.dp)
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

        content = {paddingValues->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)) {

                    TransparentHintTextField(
                        text = "",
                        hint = "Note Title",
                        onValueChange = {

                        },
                        onFocusChange = {

                        },
                        isHintVisible = true,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TransparentHintTextField(
                        text = "",
                        hint = "Note Content",
                        onValueChange = {

                        },
                        onFocusChange = {

                        },
                        isHintVisible = true,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxHeight()
                    )

            }
        }
    )
}

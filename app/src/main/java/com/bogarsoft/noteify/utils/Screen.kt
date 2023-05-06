package com.bogarsoft.noteify.utils

sealed class Screen(val route: String) {
    object NotesScreen: Screen("home_screen")
    object AddEditNoteScreen: Screen("add_note_screen")

    object ReceiveNote:Screen("receive_note")
}

package com.bogarsoft.noteify.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogarsoft.noteify.data.NoteRepository
import com.bogarsoft.noteify.models.Note
import kotlinx.coroutines.launch


class HomeViewModel(private val repository: NoteRepository):ViewModel() {
    val data = repository.data

    fun getNotes(){
        viewModelScope.launch{
            repository.getNotes()
        }
    }

    fun addNote(note:Note){
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun delete(note:Note){
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}
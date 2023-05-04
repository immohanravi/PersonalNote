package com.bogarsoft.personalnote.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bogarsoft.personalnote.data.NoteRepository
import com.bogarsoft.personalnote.models.Note
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
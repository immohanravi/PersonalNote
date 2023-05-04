package com.bogarsoft.personalnote.data

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bogarsoft.personalnote.models.Note
import org.dizitart.no2.Nitrite
import org.dizitart.no2.event.ChangeType
import org.dizitart.no2.objects.ObjectRepository
import org.dizitart.no2.objects.filters.ObjectFilters.eq


class NoteRepository(private val repo:ObjectRepository<Note>) {
    private val _data = mutableStateOf<ArrayList<Note>>(arrayListOf())
    val data: State<ArrayList<Note>> = _data

    init {
        repo.register {
            if(it.changeType == ChangeType.INSERT || it.changeType == ChangeType.UPDATE || it.changeType == ChangeType.REMOVE){
                val notesData = repo.find()
                val list = ArrayList<Note>()
                notesData.forEach { note ->
                    list.add(note)
                }
                _data.value = list
            }
        }
    }
    suspend fun getNotes(){
        val notesData = repo.find()
        Log.d(TAG, "getNotes: ${notesData.size()}")
        val list = ArrayList<Note>()
        notesData.forEach { note ->
            list.add(note)
        }

        _data.value = list
    }

    suspend fun addNote(note:Note){
        repo.insert(note)
    }

    suspend fun delete(note:Note){
        repo.remove(eq("id",note.id))
    }

    companion object{
        private const val TAG = "NoteRepository"
    }
}
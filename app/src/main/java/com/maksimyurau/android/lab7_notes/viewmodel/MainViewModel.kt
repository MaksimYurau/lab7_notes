package com.maksimyurau.android.lab7_notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksimyurau.android.lab7_notes.data.repository.Repository
import com.maksimyurau.android.lab7_notes.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Модель просмотра, используемая для хранения глобального состояния приложения.
 *
 * Эта модель просмотра используется для всех экранов.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {
    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash()
    }


    fun onCreateNewNoteClick() {
        //TODO - Open SaveNoteScreen
    }

    fun onNoteClick(note: NoteModel) {
        //TODO - Open SaveNoteScreen in Edit mode
    }

    fun onNoteCheckedChange(
        note: NoteModel
    ) {
        viewModelScope.launch(
            Dispatchers.Default
        ) {
            repository.insertNote(note)
        }
    }
}
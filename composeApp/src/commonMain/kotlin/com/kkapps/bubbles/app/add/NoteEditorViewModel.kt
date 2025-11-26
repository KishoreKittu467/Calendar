package com.kkapps.bubbles.app.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.entries.domain.entities.Note
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class NoteEditorViewModel(
    private val repository: EntryRepository
) : ViewModel() {

    @OptIn(ExperimentalTime::class)
    fun saveNote(title: String, content: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val now = Clock.System.now().toEpochMilliseconds()
            val note = Note(
                title = title,
                content = content,
                createdAt = now,
                lastModifiedAt = now
            )
            repository.saveNote(note)
            onSuccess()
        }
    }
}
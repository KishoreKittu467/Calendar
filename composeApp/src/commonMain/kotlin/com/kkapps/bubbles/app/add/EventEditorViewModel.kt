package com.kkapps.bubbles.app.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.entries.domain.entities.Event
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class EventEditorViewModel(
    private val repository: EntryRepository
) : ViewModel() {

    @OptIn(ExperimentalTime::class)
    fun saveEvent(
        name: String,
        notes: String,
        startTime: Long?,
        duration: Long,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val now = Clock.System.now().toEpochMilliseconds()
            val event = Event(
                name = name,
                notes = notes,
                createdAt = now,
                startTime = startTime ?: now,
                duration = duration,
                lastModifiedAt = now
            )
            repository.saveEvent(event)
            onSuccess()
        }
    }
}
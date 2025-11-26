package com.kkapps.bubbles.app.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.entries.domain.entities.Mood
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class MoodEditorViewModel(
    private val repository: EntryRepository
) : ViewModel() {

    @OptIn(ExperimentalTime::class)
    fun saveMood(
        moodName: String,
        moodEmoji: String,
        rank: Float,
        notes: String?,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val now = Clock.System.now().toEpochMilliseconds()
            val mood = Mood(
                moodName = moodName,
                moodEmoji = moodEmoji,
                rank = rank,
                notes = notes,
                createdAt = now
            )
            repository.saveMood(mood)
            onSuccess()
        }
    }
}
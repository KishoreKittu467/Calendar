package com.kkapps.bubbles.features.entries.presentation.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.entries.domain.entities.Diary
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class DiaryEditorViewModel(
    private val repository: EntryRepository
) : ViewModel() {

    @OptIn(ExperimentalTime::class)
    fun saveDiary(title: String, content: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val now = Clock.System.now().toEpochMilliseconds()
            val diary = Diary(
                title = title,
                content = content,
                createdAt = now,
                lastModifiedAt = now
            )
            repository.saveDiary(diary)
            onSuccess()
        }
    }
}
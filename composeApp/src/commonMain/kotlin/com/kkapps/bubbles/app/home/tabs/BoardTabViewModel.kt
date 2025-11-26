package com.kkapps.bubbles.app.home.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.flow.*
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

data class BoardEntry(
    val id: Long,
    val title: String,
    val type: String,
    val createdAt: Long
)

data class BoardUiState(
    val entriesGroupedByDate: Map<String, List<BoardEntry>> = emptyMap(),
    val isLoading: Boolean = true
)

class BoardTabViewModel(
    entryRepository: EntryRepository
) : ViewModel() {

    @OptIn(ExperimentalTime::class)
    val uiState: StateFlow<BoardUiState> = combine(
        entryRepository.getAllDiaries(),
        entryRepository.getAllNotes(),
        entryRepository.getAllEvents(),
        entryRepository.getAllTransactions(),
        entryRepository.getAllMoods()
    ) { diaries, notes, events, transactions, moods ->
        val allEntries = buildList {
            addAll(diaries.map { BoardEntry(it.id, it.title, "Diary", it.createdAt) })
            addAll(notes.map { BoardEntry(it.id, it.title, "Note", it.createdAt) })
            addAll(events.map { BoardEntry(it.id, it.name, "Event", it.createdAt) })
            addAll(transactions.map { 
                BoardEntry(it.id, "${it.payerName} - $${it.amount}", "Transaction", it.createdAt) 
            })
            addAll(moods.map { BoardEntry(it.id, "${it.moodEmoji} ${it.moodName}", "Mood", it.createdAt) })
        }

        val groupedEntries = allEntries
            .sortedByDescending { it.createdAt }
            .groupBy {
                val ldt = Instant.fromEpochMilliseconds(it.createdAt)
                    .toLocalDateTime(TimeZone.currentSystemDefault())
                "${ldt.date}"
            }
            .mapValues { entry ->
                entry.value.sortedByDescending { it.createdAt }
            }

        BoardUiState(
            entriesGroupedByDate = groupedEntries,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = BoardUiState()
    )
}
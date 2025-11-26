package com.kkapps.bubbles.features.book.data.database

import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity
import com.kkapps.bubbles.features.entries.domain.entities.Entry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class EntryDataSource {

    private val entries = mutableListOf<Entry>()
    private val _entries = MutableStateFlow(entries)

    fun upsert(entry: Entry) {
        entries.add(entry)
    }

    fun getEntries(): Flow<List<Entry>> {
        return _entries
    }

    fun getEntry(id: Long): Entry? {
        return entries.find { it.id == id }
    }

    fun deleteEntry(id: Long) {
        entries.removeAll { it.id == id }
    }

    private val diaries = mutableListOf<DiaryEntity>()
    private val _diaries = MutableStateFlow(diaries)

    fun upsertDiary(diary: DiaryEntity) {
        diaries.add(diary)
    }

    fun getDiaries(): Flow<List<DiaryEntity>> {
        return _diaries
    }

    fun getDiary(id: Long): DiaryEntity? {
        return diaries.find { it.id == id }
    }
    fun deleteDiary(id: Long) {
        diaries.removeAll { it.id == id }
    }

    fun clearDiaries() {
        diaries.clear()
    }

    fun clearEntries() {
        entries.clear()
    }

    private val notes = mutableListOf<NoteEntity>()
    private val _notes = MutableStateFlow(notes)

    fun upsertNote(note: NoteEntity) {
        notes.add(note)
    }

    fun getNotes(): Flow<List<NoteEntity>> {
        return _notes
    }

    fun getNote(id: Long): NoteEntity? {
        return notes.find { it.id == id }
    }

    fun searchNotes(query: String): List<NoteEntity> = notes.filter {
        it.title.contains(
            query,
            ignoreCase = true
        )
    }

    fun deleteNote(id: Long) {
        notes.removeAll { it.id == id }
    }

    fun clearNotes() {
        notes.clear()
    }

    private val events = mutableListOf<EventEntity>()
    private val _events = MutableStateFlow(events)

    fun upsertEvent(event: EventEntity) {
        events.add(event)
    }

    fun getEvents(): Flow<List<EventEntity>> {
        return _events
    }
    fun getEvent(id: Long): EventEntity? {
        return events.find { it.id == id }
    }

    fun deleteEvent(id: Long) {
        events.removeAll { it.id == id }
    }

    fun clearEvents() {
        events.clear()
    }

    private val transactions = mutableListOf<TransactionEntity>()
    private val _transactions = MutableStateFlow(transactions)

    fun upsertTransaction(transaction: TransactionEntity) {
        transactions.add(transaction)
    }

    fun getTransactions(): Flow<List<TransactionEntity>> {
        return _transactions
    }

    fun getTransaction(id: Long): TransactionEntity? {
        return transactions.find { it.id == id }
    }

    fun deleteTransaction(id: Long) {
        transactions.removeAll { it.id == id }
    }

    fun clearTransactions() {
        transactions.clear()
    }

    private val moods = mutableListOf<MoodEntity>()
    private val _moods = MutableStateFlow(moods)

    fun upsertMood(mood: MoodEntity) {
        moods.add(mood)
    }

    fun getMoods(): Flow<List<MoodEntity>> {
        return _moods
    }

    fun getMood(id: Long): MoodEntity? {
        return moods.find { it.id == id }
    }

    fun deleteMood(id: Long) {
        moods.removeAll { it.id == id }
    }

    fun clearMoods() {
        moods.clear()
    }

    fun clearAll() {
        clearEntries()
        clearDiaries()
        clearMoods()
        clearNotes()
        clearEvents()
        clearTransactions()
    }

}
package com.kkapps.bubbles.features.entries.domain.repository

import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.core.domain.EmptyResult
import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.features.entries.domain.entities.Diary
import com.kkapps.bubbles.features.entries.domain.entities.Event
import com.kkapps.bubbles.features.entries.domain.entities.Mood
import com.kkapps.bubbles.features.entries.domain.entities.Note
import com.kkapps.bubbles.features.entries.domain.entities.Transaction
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
    // Diary operations
    suspend fun saveDiary(diary: Diary): EmptyResult<DataError.Local>
    fun getAllDiaries(): Flow<List<Diary>>
    suspend fun getDiary(id: Long): DataResult<Diary?, DataError.Local>
    suspend fun deleteDiary(id: Long): EmptyResult<DataError.Local>

    // Note operations
    suspend fun saveNote(note: Note): EmptyResult<DataError.Local>
    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNote(id: Long): DataResult<Note?, DataError.Local>
    suspend fun deleteNote(id: Long): EmptyResult<DataError.Local>
    fun searchNotes(query: String): Flow<List<Note>>

    // Event operations
    suspend fun saveEvent(event: Event): EmptyResult<DataError.Local>
    fun getAllEvents(): Flow<List<Event>>
    fun getEventsByType(type: String): Flow<List<Event>>
    suspend fun getEvent(id: Long): DataResult<Event?, DataError.Local>
    suspend fun deleteEvent(id: Long): EmptyResult<DataError.Local>

    // Transaction operations
    suspend fun saveTransaction(transaction: Transaction): EmptyResult<DataError.Local>
    fun getAllTransactions(): Flow<List<Transaction>>
    fun getTransactionsByType(type: String): Flow<List<Transaction>>
    suspend fun getTransaction(id: Long): DataResult<Transaction?, DataError.Local>
    suspend fun deleteTransaction(id: Long): EmptyResult<DataError.Local>
    suspend fun getTotalAmountByStatus(status: String): Float

    // Mood operations
    suspend fun saveMood(mood: Mood): EmptyResult<DataError.Local>
    fun getAllMoods(): Flow<List<Mood>>
    suspend fun getMood(id: Long): DataResult<Mood?, DataError.Local>
    suspend fun deleteMood(id: Long): EmptyResult<DataError.Local>
    suspend fun getMoodCount(startDate: Long): Int
}
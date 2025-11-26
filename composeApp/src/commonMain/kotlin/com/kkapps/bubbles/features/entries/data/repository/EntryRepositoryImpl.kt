package com.kkapps.bubbles.features.entries.data.repository

import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.core.domain.EmptyResult
import com.kkapps.bubbles.features.entries.domain.entities.Diary
import com.kkapps.bubbles.features.entries.domain.entities.Event
import com.kkapps.bubbles.features.entries.domain.entities.Mood
import com.kkapps.bubbles.features.entries.domain.entities.Note
import com.kkapps.bubbles.features.entries.domain.entities.Transaction
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow

expect class EntryRepositoryImpl: EntryRepository {
    override suspend fun saveDiary(diary: Diary): EmptyResult<DataError.Local>
    override fun getAllDiaries(): Flow<List<Diary>>
    override suspend fun getDiary(id: Long): DataResult<Diary?, DataError.Local>
    override suspend fun deleteDiary(id: Long): EmptyResult<DataError.Local>
    override suspend fun saveNote(note: Note): EmptyResult<DataError.Local>
    override fun getAllNotes(): Flow<List<Note>>
    override suspend fun getNote(id: Long): DataResult<Note?, DataError.Local>
    override suspend fun deleteNote(id: Long): EmptyResult<DataError.Local>
    override fun searchNotes(query: String): Flow<List<Note>>
    override suspend fun saveEvent(event: Event): EmptyResult<DataError.Local>
    override fun getAllEvents(): Flow<List<Event>>
    override fun getEventsByType(type: String): Flow<List<Event>>
    override suspend fun getEvent(id: Long): DataResult<Event?, DataError.Local>
    override suspend fun deleteEvent(id: Long): EmptyResult<DataError.Local>
    override suspend fun saveTransaction(transaction: Transaction): EmptyResult<DataError.Local>
    override fun getAllTransactions(): Flow<List<Transaction>>
    override fun getTransactionsByType(type: String): Flow<List<Transaction>>
    override suspend fun getTransaction(id: Long): DataResult<Transaction?, DataError.Local>
    override suspend fun deleteTransaction(id: Long): EmptyResult<DataError.Local>
    override suspend fun getTotalAmountByStatus(status: String): Float
    override suspend fun saveMood(mood: Mood): EmptyResult<DataError.Local>
    override fun getAllMoods(): Flow<List<Mood>>
    override suspend fun getMood(id: Long): DataResult<Mood?, DataError.Local>
    override suspend fun deleteMood(id: Long): EmptyResult<DataError.Local>
    override suspend fun getMoodCount(startDate: Long): Int
}
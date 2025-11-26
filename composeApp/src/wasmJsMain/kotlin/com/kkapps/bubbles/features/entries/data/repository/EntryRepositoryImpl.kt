package com.kkapps.bubbles.features.entries.data.repository

import com.kkapps.bubbles.features.book.data.database.EntryDataSource
import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.core.domain.EmptyResult
import com.kkapps.bubbles.features.entries.data.mapper.toDiary
import com.kkapps.bubbles.features.entries.data.mapper.toDiaryEntity
import com.kkapps.bubbles.features.entries.data.mapper.toEvent
import com.kkapps.bubbles.features.entries.data.mapper.toEventEntity
import com.kkapps.bubbles.features.entries.data.mapper.toMood
import com.kkapps.bubbles.features.entries.data.mapper.toMoodEntity
import com.kkapps.bubbles.features.entries.data.mapper.toNote
import com.kkapps.bubbles.features.entries.data.mapper.toNoteEntity
import com.kkapps.bubbles.features.entries.data.mapper.toTransaction
import com.kkapps.bubbles.features.entries.data.mapper.toTransactionEntity
import com.kkapps.bubbles.features.entries.domain.entities.Diary
import com.kkapps.bubbles.features.entries.domain.entities.Event
import com.kkapps.bubbles.features.entries.domain.entities.Mood
import com.kkapps.bubbles.features.entries.domain.entities.Note
import com.kkapps.bubbles.features.entries.domain.entities.Transaction
import com.kkapps.bubbles.features.entries.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map
import kotlin.collections.map

actual class EntryRepositoryImpl(
    private val dataSource: EntryDataSource
): EntryRepository  {

    // Diary operations
    actual override suspend fun saveDiary(diary: Diary): EmptyResult<DataError.Local> {
            dataSource.upsertDiary(diary.toDiaryEntity())
            return DataResult.Success(Unit)
    }

    actual override fun getAllDiaries(): Flow<List<Diary>> {
        return dataSource.getDiaries().map { entities ->
            entities.map { it.toDiary() }
        }
    }

    actual override suspend fun getDiary(id: Long): DataResult<Diary?, DataError.Local> {
            val diary = dataSource.getDiary(id)?.toDiary()
            return DataResult.Success(diary)
    }

    actual override suspend fun deleteDiary(id: Long): EmptyResult<DataError.Local> {
            dataSource.deleteDiary(id)
        return DataResult.Success(Unit)
    }

    // Note operations
    actual override suspend fun saveNote(note: Note): EmptyResult<DataError.Local> {
            dataSource.upsertNote(note.toNoteEntity())
        return DataResult.Success(Unit)
    }

    actual override fun getAllNotes(): Flow<List<Note>> {
        return dataSource.getNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    actual override suspend fun getNote(id: Long): DataResult<Note?, DataError.Local> {
            val note = dataSource.getNote(id)?.toNote()
            return DataResult.Success(note)
    }

    actual override suspend fun deleteNote(id: Long): EmptyResult<DataError.Local> {
            dataSource.deleteNote(id)
        return DataResult.Success(Unit)
    }

    actual override fun searchNotes(query: String): Flow<List<Note>> {
        return dataSource.getNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    // Event operations
    actual override suspend fun saveEvent(event: Event): EmptyResult<DataError.Local> {
            dataSource.upsertEvent(event.toEventEntity())
        return DataResult.Success(Unit)
    }

    actual override fun getAllEvents(): Flow<List<Event>> {
        return dataSource.getEvents().map { entities ->
            entities.map { it.toEvent() }
        }
    }

    actual override fun getEventsByType(type: String): Flow<List<Event>> {
        return dataSource.getEvents().map { entities ->
            entities.map { it.toEvent() }
        }
    }

    actual override suspend fun getEvent(id: Long): DataResult<Event?, DataError.Local> {
            val event = dataSource.getEvent(id)?.toEvent()
        return DataResult.Success(event)
    }

    actual override suspend fun deleteEvent(id: Long): EmptyResult<DataError.Local> {
            dataSource.deleteEvent(id)
            return DataResult.Success(Unit)
    }

    // Transaction operations
    actual override suspend fun saveTransaction(transaction: Transaction): EmptyResult<DataError.Local> {
            dataSource.upsertTransaction(transaction.toTransactionEntity())
        return DataResult.Success(Unit)
    }

    actual override fun getAllTransactions(): Flow<List<Transaction>> {
        return dataSource.getTransactions().map { entities ->
            entities.map { it.toTransaction() }
        }
    }

    actual override fun getTransactionsByType(type: String): Flow<List<Transaction>> {
        return dataSource.getTransactions().map { entities ->
            entities.map { it.toTransaction() }
        }
    }

    actual override suspend fun getTransaction(id: Long): DataResult<Transaction?, DataError.Local> {
            val transaction = dataSource.getTransaction(id)?.toTransaction()
            return DataResult.Success(transaction)
    }

    actual override suspend fun deleteTransaction(id: Long): EmptyResult<DataError.Local> {
            dataSource.deleteTransaction(id)
        return DataResult.Success(Unit)
    }

    actual override suspend fun getTotalAmountByStatus(status: String): Float {
        return 0f
    }

    // Mood operations
    actual override suspend fun saveMood(mood: Mood): EmptyResult<DataError.Local> {
            dataSource.upsertMood(mood.toMoodEntity())
            return DataResult.Success(Unit)
    }

    actual override fun getAllMoods(): Flow<List<Mood>> {
        return dataSource.getMoods().map { entities ->
            entities.map { it.toMood() }
        }
    }

    actual override suspend fun getMood(id: Long): DataResult<Mood?, DataError.Local> {
            val mood = dataSource.getMood(id)?.toMood()
            return DataResult.Success(mood)
    }

    actual override suspend fun deleteMood(id: Long): EmptyResult<DataError.Local> {
            dataSource.deleteMood(id)
            return DataResult.Success(Unit)
        }

    actual override suspend fun getMoodCount(startDate: Long): Int {
        return dataSource.getMoods().count()
    }
}
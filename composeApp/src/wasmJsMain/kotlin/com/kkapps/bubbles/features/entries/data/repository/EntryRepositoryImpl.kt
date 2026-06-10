package com.kkapps.bubbles.features.entries.data.repository

import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.core.domain.EmptyResult
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDao
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
import kotlinx.coroutines.flow.map
import kotlin.collections.map

actual class EntryRepositoryImpl(
    private val dao: FavoriteBookDao
): EntryRepository  {

    // Diary operations
    actual override suspend fun saveDiary(diary: Diary): EmptyResult<DataError.Local> {
            dao.upsertDiary(diary.toDiaryEntity())
            return DataResult.Success(Unit)
    }

    actual override fun getAllDiaries(): Flow<List<Diary>> {
        return dao.getAllDiaries().map { entities ->
            entities.map { it.toDiary() }
        }
    }

    actual override suspend fun getDiary(id: Long): DataResult<Diary?, DataError.Local> {
            val diary = dao.getDiary(id)?.toDiary()
            return DataResult.Success(diary)
    }

    actual override suspend fun deleteDiary(id: Long): EmptyResult<DataError.Local> {
            dao.deleteDiary(id)
        return DataResult.Success(Unit)
    }

    // Note operations
    actual override suspend fun saveNote(note: Note): EmptyResult<DataError.Local> {
            dao.upsertNote(note.toNoteEntity())
        return DataResult.Success(Unit)
    }

    actual override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    actual override suspend fun getNote(id: Long): DataResult<Note?, DataError.Local> {
            val note = dao.getNote(id)?.toNote()
            return DataResult.Success(note)
    }

    actual override suspend fun deleteNote(id: Long): EmptyResult<DataError.Local> {
            dao.deleteNote(id)
        return DataResult.Success(Unit)
    }

    actual override fun searchNotes(query: String): Flow<List<Note>> {
        return dao.getAllNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    // Event operations
    actual override suspend fun saveEvent(event: Event): EmptyResult<DataError.Local> {
            dao.upsertEvent(event.toEventEntity())
        return DataResult.Success(Unit)
    }

    actual override fun getAllEvents(): Flow<List<Event>> {
        return dao.getAllEvents().map { entities ->
            entities.map { it.toEvent() }
        }
    }

    actual override fun getEventsByType(type: String): Flow<List<Event>> {
        return dao.getAllEvents().map { entities ->
            entities.map { it.toEvent() }
        }
    }

    actual override suspend fun getEvent(id: Long): DataResult<Event?, DataError.Local> {
            val event = dao.getEvent(id)?.toEvent()
        return DataResult.Success(event)
    }

    actual override suspend fun deleteEvent(id: Long): EmptyResult<DataError.Local> {
            dao.deleteEvent(id)
            return DataResult.Success(Unit)
    }

    // Transaction operations
    actual override suspend fun saveTransaction(transaction: Transaction): EmptyResult<DataError.Local> {
            dao.upsertTransaction(transaction.toTransactionEntity())
        return DataResult.Success(Unit)
    }

    actual override fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAllTransactions().map { entities ->
            entities.map { it.toTransaction() }
        }
    }

    actual override fun getTransactionsByType(type: String): Flow<List<Transaction>> {
        return dao.getAllTransactions().map { entities ->
            entities.map { it.toTransaction() }
        }
    }

    actual override suspend fun getTransaction(id: Long): DataResult<Transaction?, DataError.Local> {
            val transaction = dao.getTransaction(id)?.toTransaction()
            return DataResult.Success(transaction)
    }

    actual override suspend fun deleteTransaction(id: Long): EmptyResult<DataError.Local> {
            dao.deleteTransaction(id)
        return DataResult.Success(Unit)
    }

    actual override suspend fun getTotalAmountByStatus(status: String): Float {
        return 0f
    }

    // Mood operations
    actual override suspend fun saveMood(mood: Mood): EmptyResult<DataError.Local> {
            dao.upsertMood(mood.toMoodEntity())
            return DataResult.Success(Unit)
    }

    actual override fun getAllMoods(): Flow<List<Mood>> {
        return dao.getAllMoods().map { entities ->
            entities.map { it.toMood() }
        }
    }

    actual override suspend fun getMood(id: Long): DataResult<Mood?, DataError.Local> {
            val mood = dao.getMood(id)?.toMood()
            return DataResult.Success(mood)
    }

    actual override suspend fun deleteMood(id: Long): EmptyResult<DataError.Local> {
            dao.deleteMood(id)
            return DataResult.Success(Unit)
        }

    actual override suspend fun getMoodCount(startDate: Long): Int {
        return dao.getMoodCount(startDate)
    }
}
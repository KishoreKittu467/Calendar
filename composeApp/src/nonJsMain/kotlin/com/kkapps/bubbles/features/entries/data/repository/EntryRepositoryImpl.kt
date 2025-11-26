package com.kkapps.bubbles.features.entries.data.repository

import androidx.sqlite.SQLiteException
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

actual class EntryRepositoryImpl(
    private val dao: FavoriteBookDao
): EntryRepository  {

    // Diary operations
    actual override suspend fun saveDiary(diary: Diary): EmptyResult<DataError.Local> {
        return try {
            dao.upsertDiary(diary.toDiaryEntity())
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override fun getAllDiaries(): Flow<List<Diary>> {
        return dao.getAllDiaries().map { entities ->
            entities.map { it.toDiary() }
        }
    }

    actual override suspend fun getDiary(id: Long): DataResult<Diary?, DataError.Local> {
        return try {
            val diary = dao.getDiary(id)?.toDiary()
            DataResult.Success(diary)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun deleteDiary(id: Long): EmptyResult<DataError.Local> {
        return try {
            dao.deleteDiary(id)
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    // Note operations
    actual override suspend fun saveNote(note: Note): EmptyResult<DataError.Local> {
        return try {
            dao.upsertNote(note.toNoteEntity())
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    actual override suspend fun getNote(id: Long): DataResult<Note?, DataError.Local> {
        return try {
            val note = dao.getNote(id)?.toNote()
            DataResult.Success(note)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun deleteNote(id: Long): EmptyResult<DataError.Local> {
        return try {
            dao.deleteNote(id)
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override fun searchNotes(query: String): Flow<List<Note>> {
        return dao.searchNotes(query).map { entities ->
            entities.map { it.toNote() }
        }
    }

    // Event operations
    actual override suspend fun saveEvent(event: Event): EmptyResult<DataError.Local> {
        return try {
            dao.upsertEvent(event.toEventEntity())
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override fun getAllEvents(): Flow<List<Event>> {
        return dao.getAllEvents().map { entities ->
            entities.map { it.toEvent() }
        }
    }

    actual override fun getEventsByType(type: String): Flow<List<Event>> {
        return dao.getEventsByType(type).map { entities ->
            entities.map { it.toEvent() }
        }
    }

    actual override suspend fun getEvent(id: Long): DataResult<Event?, DataError.Local> {
        return try {
            val event = dao.getEvent(id)?.toEvent()
            DataResult.Success(event)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun deleteEvent(id: Long): EmptyResult<DataError.Local> {
        return try {
            dao.deleteEvent(id)
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    // Transaction operations
    actual override suspend fun saveTransaction(transaction: Transaction): EmptyResult<DataError.Local> {
        return try {
            dao.upsertTransaction(transaction.toTransactionEntity())
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override fun getAllTransactions(): Flow<List<Transaction>> {
        return dao.getAllTransactions().map { entities ->
            entities.map { it.toTransaction() }
        }
    }

    actual override fun getTransactionsByType(type: String): Flow<List<Transaction>> {
        return dao.getTransactionsByType(type).map { entities ->
            entities.map { it.toTransaction() }
        }
    }

    actual override suspend fun getTransaction(id: Long): DataResult<Transaction?, DataError.Local> {
        return try {
            val transaction = dao.getTransaction(id)?.toTransaction()
            DataResult.Success(transaction)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun deleteTransaction(id: Long): EmptyResult<DataError.Local> {
        return try {
            dao.deleteTransaction(id)
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun getTotalAmountByStatus(status: String): Float {
        return dao.getTotalAmountByStatus(status) ?: 0f
    }

    // Mood operations
    actual override suspend fun saveMood(mood: Mood): EmptyResult<DataError.Local> {
        return try {
            dao.upsertMood(mood.toMoodEntity())
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override fun getAllMoods(): Flow<List<Mood>> {
        return dao.getAllMoods().map { entities ->
            entities.map { it.toMood() }
        }
    }

    actual override suspend fun getMood(id: Long): DataResult<Mood?, DataError.Local> {
        return try {
            val mood = dao.getMood(id)?.toMood()
            DataResult.Success(mood)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun deleteMood(id: Long): EmptyResult<DataError.Local> {
        return try {
            dao.deleteMood(id)
            DataResult.Success(Unit)
        } catch (_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    actual override suspend fun getMoodCount(startDate: Long): Int {
        return dao.getMoodCount(startDate)
    }
}
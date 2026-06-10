package com.kkapps.bubbles.features.book.data.database

import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.HabitEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TaskEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

actual abstract class FavoriteBookDao {

    // Books
    actual abstract suspend fun upsert(book: BookEntity)

    actual abstract fun getFavoriteBooks(): Flow<List<BookEntity>>

    actual abstract suspend fun getFavoriteBook(id: String): BookEntity?

    actual abstract suspend fun deleteFavoriteBook(id: String)

    // Diary
    actual abstract suspend fun upsertDiary(diary: DiaryEntity)

    actual abstract fun getAllDiaries(): Flow<List<DiaryEntity>>

    actual abstract suspend fun getDiary(id: Long): DiaryEntity?

    actual abstract suspend fun deleteDiary(id: Long)

    // Events
    actual abstract suspend fun upsertEvent(event: EventEntity)

    actual abstract fun getAllEvents(): Flow<List<EventEntity>>

    actual abstract fun getEventsByType(type: String): Flow<List<EventEntity>>

    actual abstract suspend fun getEvent(id: Long): EventEntity?

    actual abstract suspend fun deleteEvent(id: Long)

    // Tasks
    actual abstract suspend fun upsertTask(task: TaskEntity)

    actual abstract fun getAllTasks(): Flow<List<TaskEntity>>

    actual abstract fun getTasksByStatus(isCompleted: Boolean): Flow<List<TaskEntity>>

    actual abstract suspend fun getTask(id: Long): TaskEntity?

    actual abstract suspend fun deleteTask(id: Long)

    // Transactions
    actual abstract suspend fun upsertTransaction(transaction: TransactionEntity)

    actual abstract fun getAllTransactions(): Flow<List<TransactionEntity>>

    actual abstract fun getTransactionsByType(type: String): Flow<List<TransactionEntity>>

    actual abstract suspend fun getTransaction(id: Long): TransactionEntity?

    actual abstract suspend fun deleteTransaction(id: Long)

    actual abstract suspend fun getTotalAmountByStatus(status: String): Float?

    // Habits
    actual abstract suspend fun upsertHabit(habit: HabitEntity)

    actual abstract fun getAllHabits(): Flow<List<HabitEntity>>

    actual abstract suspend fun getHabit(id: Long): HabitEntity?

    actual abstract suspend fun deleteHabit(id: Long)

    actual abstract suspend fun updateHabitStreak(id: Long, streak: Int)

    // Moods
    actual abstract suspend fun upsertMood(mood: MoodEntity)

    actual abstract fun getAllMoods(): Flow<List<MoodEntity>>

    actual abstract suspend fun getMood(id: Long): MoodEntity?

    actual abstract suspend fun deleteMood(id: Long)

    actual abstract suspend fun getMoodCount(startDate: Long): Int

    // Notes
    actual abstract suspend fun upsertNote(note: NoteEntity)

    actual abstract fun getAllNotes(): Flow<List<NoteEntity>>

    actual abstract suspend fun getNote(id: Long): NoteEntity?

    actual abstract suspend fun deleteNote(id: Long)

    actual abstract fun searchNotes(query: String): Flow<List<NoteEntity>>
}
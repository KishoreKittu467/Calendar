package com.kkapps.bubbles.features.book.data.database

import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.HabitEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TaskEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

expect abstract class FavoriteBookDao {

    // Books
    abstract suspend fun upsert(book: BookEntity)

    abstract fun getFavoriteBooks(): Flow<List<BookEntity>>

    abstract suspend fun getFavoriteBook(id: String): BookEntity?

    abstract suspend fun deleteFavoriteBook(id: String)

    // Diary
    abstract suspend fun upsertDiary(diary: DiaryEntity)

    abstract fun getAllDiaries(): Flow<List<DiaryEntity>>

    abstract suspend fun getDiary(id: Long): DiaryEntity?

    abstract suspend fun deleteDiary(id: Long)

    // Events
    abstract suspend fun upsertEvent(event: EventEntity)

    abstract fun getAllEvents(): Flow<List<EventEntity>>

    abstract fun getEventsByType(type: String): Flow<List<EventEntity>>

    abstract suspend fun getEvent(id: Long): EventEntity?

    abstract suspend fun deleteEvent(id: Long)

    // Tasks
    abstract suspend fun upsertTask(task: TaskEntity)

    abstract fun getAllTasks(): Flow<List<TaskEntity>>

    abstract fun getTasksByStatus(isCompleted: Boolean): Flow<List<TaskEntity>>

    abstract suspend fun getTask(id: Long): TaskEntity?

    abstract suspend fun deleteTask(id: Long)

    // Transactions
    abstract suspend fun upsertTransaction(transaction: TransactionEntity)

    abstract fun getAllTransactions(): Flow<List<TransactionEntity>>

    abstract fun getTransactionsByType(type: String): Flow<List<TransactionEntity>>

    abstract suspend fun getTransaction(id: Long): TransactionEntity?

    abstract suspend fun deleteTransaction(id: Long)

    abstract suspend fun getTotalAmountByStatus(status: String): Float?

    // Habits
    abstract suspend fun upsertHabit(habit: HabitEntity)

    abstract fun getAllHabits(): Flow<List<HabitEntity>>

    abstract suspend fun getHabit(id: Long): HabitEntity?

    abstract suspend fun deleteHabit(id: Long)

    abstract suspend fun updateHabitStreak(id: Long, streak: Int)

    // Moods
    abstract suspend fun upsertMood(mood: MoodEntity)

    abstract fun getAllMoods(): Flow<List<MoodEntity>>

    abstract suspend fun getMood(id: Long): MoodEntity?

    abstract suspend fun deleteMood(id: Long)

    abstract suspend fun getMoodCount(startDate: Long): Int

    // Notes
    abstract suspend fun upsertNote(note: NoteEntity)

    abstract fun getAllNotes(): Flow<List<NoteEntity>>

    abstract suspend fun getNote(id: Long): NoteEntity?

    abstract suspend fun deleteNote(id: Long)

    abstract fun searchNotes(query: String): Flow<List<NoteEntity>>

}
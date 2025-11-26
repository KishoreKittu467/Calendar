package com.kkapps.bubbles.features.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.HabitEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TaskEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteBookDao {

    // Books
    @Upsert
    abstract suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM BookEntity")
    abstract fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    abstract suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :id")
    abstract suspend fun deleteFavoriteBook(id: String)

    // Diary
    @Upsert
    abstract suspend fun upsertDiary(diary: DiaryEntity)

    @Query("SELECT * FROM diary ORDER BY createdAt DESC")
    abstract fun getAllDiaries(): Flow<List<DiaryEntity>>

    @Query("SELECT * FROM diary WHERE id = :id")
    abstract suspend fun getDiary(id: Long): DiaryEntity?

    @Query("DELETE FROM diary WHERE id = :id")
    abstract suspend fun deleteDiary(id: Long)

    // Events
    @Upsert
    abstract suspend fun upsertEvent(event: EventEntity)

    @Query("SELECT * FROM events ORDER BY startTime DESC")
    abstract fun getAllEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE eventType = :type ORDER BY startTime DESC")
    abstract fun getEventsByType(type: String): Flow<List<EventEntity>>

    @Query("SELECT * FROM events WHERE id = :id")
    abstract suspend fun getEvent(id: Long): EventEntity?

    @Query("DELETE FROM events WHERE id = :id")
    abstract suspend fun deleteEvent(id: Long)

    // Tasks
    @Upsert
    abstract suspend fun upsertTask(task: TaskEntity)

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    abstract fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isCompleted = :isCompleted ORDER BY dueDate ASC")
    abstract fun getTasksByStatus(isCompleted: Boolean): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    abstract suspend fun getTask(id: Long): TaskEntity?

    @Query("DELETE FROM tasks WHERE id = :id")
    abstract suspend fun deleteTask(id: Long)

    // Transactions
    @Upsert
    abstract suspend fun upsertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY createdAt DESC")
    abstract fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE txType = :type ORDER BY createdAt DESC")
    abstract fun getTransactionsByType(type: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    abstract suspend fun getTransaction(id: Long): TransactionEntity?

    @Query("DELETE FROM transactions WHERE id = :id")
    abstract suspend fun deleteTransaction(id: Long)

    @Query("SELECT SUM(amount) FROM transactions WHERE txStatus = :status")
    abstract suspend fun getTotalAmountByStatus(status: String): Float?

    // Habits
    @Upsert
    abstract suspend fun upsertHabit(habit: HabitEntity)

    @Query("SELECT * FROM habits ORDER BY createdAt DESC")
    abstract fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("SELECT * FROM habits WHERE id = :id")
    abstract suspend fun getHabit(id: Long): HabitEntity?

    @Query("DELETE FROM habits WHERE id = :id")
    abstract suspend fun deleteHabit(id: Long)

    @Query("UPDATE habits SET longestStreak = :streak WHERE id = :id")
    abstract suspend fun updateHabitStreak(id: Long, streak: Int)

    // Moods
    @Upsert
    abstract suspend fun upsertMood(mood: MoodEntity)

    @Query("SELECT * FROM moods ORDER BY createdAt DESC")
    abstract fun getAllMoods(): Flow<List<MoodEntity>>

    @Query("SELECT * FROM moods WHERE id = :id")
    abstract suspend fun getMood(id: Long): MoodEntity?

    @Query("DELETE FROM moods WHERE id = :id")
    abstract suspend fun deleteMood(id: Long)

    @Query("SELECT COUNT(*) FROM moods WHERE createdAt >= :startDate")
    abstract suspend fun getMoodCount(startDate: Long): Int

    // Notes
    @Upsert
    abstract suspend fun upsertNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY lastModifiedAt DESC")
    abstract fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    abstract suspend fun getNote(id: Long): NoteEntity?

    @Query("DELETE FROM notes WHERE id = :id")
    abstract suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    abstract fun searchNotes(query: String): Flow<List<NoteEntity>>
}
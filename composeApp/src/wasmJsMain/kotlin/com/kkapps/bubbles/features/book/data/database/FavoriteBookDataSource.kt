package com.kkapps.bubbles.features.book.data.database

import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.HabitEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TaskEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FavoriteBookDataSource : FavoriteBookDao() {

    private val favoriteBooks = mutableListOf<BookEntity>()
    private val _favoriteBooks = MutableStateFlow(favoriteBooks)

    private val notes = mutableListOf<NoteEntity>()
    private val _notes = MutableStateFlow(notes)

    private val events = mutableListOf<EventEntity>()
    private val _events = MutableStateFlow(events)

    private val tasks = mutableListOf<TaskEntity>()
    private val _tasks = MutableStateFlow(tasks)

    private val habits = mutableListOf<HabitEntity>()
    private val _habits = MutableStateFlow(habits)

    private val moods = mutableListOf<MoodEntity>()
    private val _moods = MutableStateFlow(moods)

    private val transactions = mutableListOf<TransactionEntity>()
    private val _transactions = MutableStateFlow(transactions)

    private val diaries = mutableListOf<DiaryEntity>()
    private val _diaries = MutableStateFlow(diaries)

    override suspend fun upsert(book: BookEntity) {
        favoriteBooks.add(book)
    }

    override fun getFavoriteBooks(): Flow<List<BookEntity>> {
        return _favoriteBooks
    }

    override suspend fun getFavoriteBook(id: String): BookEntity? {
        return favoriteBooks.find { it.id == id }
    }

    override suspend fun deleteFavoriteBook(id: String) {
        favoriteBooks.removeAll { it.id == id }
    }

    override suspend fun upsertDiary(diary: DiaryEntity) {
        diaries.add(diary)
    }

    override fun getAllDiaries(): Flow<List<DiaryEntity>> {
        return _diaries
    }

    override suspend fun getDiary(id: Long): DiaryEntity? {
        return diaries.find { it.id == id }
    }

    override suspend fun deleteDiary(id: Long) {
        diaries.removeAll { it.id == id }
    }

    override suspend fun upsertEvent(event: EventEntity) {
        events.add(event)
    }

    override fun getAllEvents(): Flow<List<EventEntity>> {
        return _events
    }

    override fun getEventsByType(type: String): Flow<List<EventEntity>> {
        return _events.map { events ->
            events.filter { it.eventType == type }
        }
    }

    override suspend fun getEvent(id: Long): EventEntity? {
        return events.find { it.id == id }
    }

    override suspend fun deleteEvent(id: Long) {
        events.removeAll { it.id == id }
    }

    override suspend fun upsertTask(task: TaskEntity) {
        tasks.add(task)
    }

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return _tasks
    }

    override fun getTasksByStatus(isCompleted: Boolean): Flow<List<TaskEntity>> {
        return _tasks.map { tasks ->
            tasks.filter { it.isCompleted == isCompleted }
        }
    }

    override suspend fun getTask(id: Long): TaskEntity? {
        return tasks.find { it.id == id }
    }

    override suspend fun deleteTask(id: Long) {
        tasks.removeAll { it.id == id }
    }

    override suspend fun upsertTransaction(transaction: TransactionEntity) {
        transactions.add(transaction)
    }

    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return _transactions
    }

    override fun getTransactionsByType(type: String): Flow<List<TransactionEntity>> {
        return _transactions.map { transactions ->
            transactions.filter { it.txType == type }
        }
    }

    override suspend fun getTransaction(id: Long): TransactionEntity? {
        return transactions.find { it.id == id }
    }

    override suspend fun deleteTransaction(id: Long) {
        transactions.removeAll { it.id == id }
    }

    override suspend fun getTotalAmountByStatus(status: String): Float? {
        return 0f
    }

    override suspend fun upsertHabit(habit: HabitEntity) {
        habits.add(habit)
    }

    override fun getAllHabits(): Flow<List<HabitEntity>> {
        return _habits
    }

    override suspend fun getHabit(id: Long): HabitEntity? {
        return habits.find { it.id == id }
    }

    override suspend fun deleteHabit(id: Long) {
        habits.removeAll { it.id == id }
    }

    override suspend fun updateHabitStreak(id: Long, streak: Int) {
        val habit = habits.find { it.id == id } ?: return
    }

    override suspend fun upsertMood(mood: MoodEntity) {
        moods.add(mood)
    }

    override fun getAllMoods(): Flow<List<MoodEntity>> {
        return _moods
    }

    override suspend fun getMood(id: Long): MoodEntity? {
        return moods.find { it.id == id }
    }

    override suspend fun deleteMood(id: Long) {
        moods.removeAll { it.id == id }
    }

    override suspend fun getMoodCount(startDate: Long): Int {
        return moods.count()
    }

    override suspend fun upsertNote(note: NoteEntity) {
        notes.add(note)
    }

    override fun getAllNotes(): Flow<List<NoteEntity>> {
        return _notes
    }

    override suspend fun getNote(id: Long): NoteEntity? {
        return notes.find { it.id == id }
    }

    override suspend fun deleteNote(id: Long) {
        notes.removeAll { it.id == id }
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return _notes.map { notes ->
            notes.filter { it.title.contains(query, true) || it.content.contains(query, true) }
        }
    }
}
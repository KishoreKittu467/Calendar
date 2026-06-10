package com.kkapps.bubbles.app.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

actual class DatabaseEditorViewModel (
    private val dao: FavoriteBookDao
) : ViewModel() {

    actual fun loadTableData(
        tableName: String,
        onDataLoaded: (rows: List<Map<String, String>>, columns: List<String>) -> Unit
    ) {
        viewModelScope.launch {
            when (tableName) {
                "BookEntity" -> {
                    val books = dao.getFavoriteBooks().first()
                    val columns = listOf("id", "title", "description", "imageUrl", "authors", "firstPublishYear")
                    val rows = books.map { book ->
                        mapOf(
                            "id" to book.id,
                            "title" to book.title,
                            "description" to (book.description ?: ""),
                            "imageUrl" to book.imageUrl,
                            "authors" to book.authors.joinToString(", "),
                            "firstPublishYear" to (book.firstPublishYear ?: "")
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "diary" -> {
                    val diaries = dao.getAllDiaries().first()
                    val columns = listOf("id", "title", "content", "createdAt", "lastModifiedAt", "tags")
                    val rows = diaries.map { diary ->
                        mapOf(
                            "id" to diary.id.toString(),
                            "title" to diary.title,
                            "content" to diary.content,
                            "createdAt" to diary.createdAt.toString(),
                            "lastModifiedAt" to diary.lastModifiedAt.toString(),
                            "tags" to (diary.tags ?: "")
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "notes" -> {
                    val notes = dao.getAllNotes().first()
                    val columns = listOf("id", "title", "content", "createdAt", "lastModifiedAt", "tags")
                    val rows = notes.map { note ->
                        mapOf(
                            "id" to note.id.toString(),
                            "title" to note.title,
                            "content" to note.content,
                            "createdAt" to note.createdAt.toString(),
                            "lastModifiedAt" to note.lastModifiedAt.toString(),
                            "tags" to (note.tags ?: "")
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "events" -> {
                    val events = dao.getAllEvents().first()
                    val columns = listOf("id", "name", "notes", "startTime", "duration", "eventType")
                    val rows = events.map { event ->
                        mapOf(
                            "id" to event.id.toString(),
                            "name" to event.name,
                            "notes" to (event.notes ?: ""),
                            "startTime" to event.startTime.toString(),
                            "duration" to event.duration.toString(),
                            "eventType" to event.eventType
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "tasks" -> {
                    val tasks = dao.getAllTasks().first()
                    val columns = listOf("id", "name", "description", "dueDate", "isCompleted", "priority")
                    val rows = tasks.map { task ->
                        mapOf(
                            "id" to task.id.toString(),
                            "name" to task.name,
                            "description" to (task.description ?: ""),
                            "dueDate" to (task.dueDate?.toString() ?: ""),
                            "isCompleted" to task.isCompleted.toString(),
                            "priority" to task.priority.toString()
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "transactions" -> {
                    val transactions = dao.getAllTransactions().first()
                    val columns = listOf("id", "amount", "payerName", "receiverName", "txType", "notes")
                    val rows = transactions.map { tx ->
                        mapOf(
                            "id" to tx.id.toString(),
                            "amount" to tx.amount.toString(),
                            "payerName" to tx.payerName,
                            "receiverName" to (tx.receiverName ?: ""),
                            "txType" to (tx.txType ?: ""),
                            "notes" to (tx.notes ?: "")
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "habits" -> {
                    val habits = dao.getAllHabits().first()
                    val columns = listOf("id", "name", "description", "habitType", "rank", "longestStreak")
                    val rows = habits.map { habit ->
                        mapOf(
                            "id" to habit.id.toString(),
                            "name" to habit.name,
                            "description" to (habit.description ?: ""),
                            "habitType" to habit.habitType,
                            "rank" to (habit.rank?.toString() ?: ""),
                            "longestStreak" to habit.longestStreak.toString()
                        )
                    }
                    onDataLoaded(rows, columns)
                }
                "moods" -> {
                    val moods = dao.getAllMoods().first()
                    val columns = listOf("id", "moodName", "moodEmoji", "rank", "notes", "createdAt")
                    val rows = moods.map { mood ->
                        mapOf(
                            "id" to mood.id.toString(),
                            "moodName" to mood.moodName,
                            "moodEmoji" to mood.moodEmoji,
                            "rank" to mood.rank.toString(),
                            "notes" to (mood.notes ?: ""),
                            "createdAt" to mood.createdAt.toString()
                        )
                    }
                    onDataLoaded(rows, columns)
                }
            }
        }
    }

    actual fun saveTableData(tableName: String, rows: List<Map<String, String>>) {
        viewModelScope.launch {
            // Note: Actual implementation would need proper entity conversion and update logic
            // This is a simplified version for demonstration
            // In production, you'd need to properly convert strings back to typed entities
            // and use dao.upsert methods
        }
    }
}
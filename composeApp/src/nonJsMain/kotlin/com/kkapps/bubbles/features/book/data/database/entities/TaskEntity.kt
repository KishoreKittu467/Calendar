package com.kkapps.bubbles.features.book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
actual data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String? = null,
    val createdAt: Long,
    val dueDate: Long? = null,
    val isCompleted: Boolean = false,
    val priority: Int = 0, // 0=Low, 1=Medium, 2=High
    val tags: String? = null,
    val categories: String? = null,
    val lastModifiedAt: Long,
    val imageEmoji: String? = null
)
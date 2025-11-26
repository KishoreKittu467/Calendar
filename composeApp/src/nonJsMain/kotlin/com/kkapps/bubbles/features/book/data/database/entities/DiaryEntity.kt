package com.kkapps.bubbles.features.book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
actual data class DiaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val imageEmoji: String? = null,
    val tags: String? = null, // Comma-separated
    val categories: String? = null, // Comma-separated
    val isMarked: Boolean = false
)
package com.kkapps.bubbles.features.book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
actual data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val tags: String? = null,
    val categories: String? = null,
    val imageEmoji: String? = null,
    val isMarked: Boolean = false
)
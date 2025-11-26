package com.kkapps.bubbles.features.book.data.database.entities

actual data class NoteEntity(
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val tags: String? = null,
    val categories: String? = null,
    val imageEmoji: String? = null,
    val isMarked: Boolean = false
)
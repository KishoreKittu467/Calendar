package com.kkapps.bubbles.features.book.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class HabitDto(
    val id: Long = 0,
    val name: String,
    val notes: String?,
    val type: String,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val imageEmoji: String? = null,
    val tags: String? = null, // Comma-separated
    val categories: String? = null, // Comma-separated
    val isMarked: Boolean = false
)
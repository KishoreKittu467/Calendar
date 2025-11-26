package com.kkapps.bubbles.features.book.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class DiaryDto(
    val id: Long = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val lastModifiedAt: Long,
    val imageEmoji: String? = null,
    val tags: String? = null, // Comma-separated
    val categories: String? = null, // Comma-separated
    val isMarked: Boolean = false
)
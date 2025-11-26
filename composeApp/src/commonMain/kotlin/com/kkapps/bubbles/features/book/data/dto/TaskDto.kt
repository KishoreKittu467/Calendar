package com.kkapps.bubbles.features.book.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val id: Long = 0,
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
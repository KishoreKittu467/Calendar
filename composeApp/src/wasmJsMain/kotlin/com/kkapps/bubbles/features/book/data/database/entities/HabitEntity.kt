package com.kkapps.bubbles.features.book.data.database.entities

actual data class HabitEntity(
    val id: Long = 0,
    val name: String,
    val description: String? = null,
    val createdAt: Long,
    val startTime: Long,
    val habitType: String = "Default", // Default, MilestoneOnly, ScoreOnly
    val rank: Float? = null, // Score
    val milestoneReached: String? = null, // Milestone name
    val longestStreak: Int = 0,
    val tags: String? = null,
    val categories: String? = null,
    val lastModifiedAt: Long,
    val imageEmoji: String? = null
)
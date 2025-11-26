package com.kkapps.bubbles.features.book.data.database.entities

actual data class MoodEntity(
    val id: Long = 0,
    val moodName: String, // SAD, OKAY, HAPPY, JOY
    val moodEmoji: String,
    val rank: Float,
    val notes: String? = null,
    val createdAt: Long,
    val imagePhoto: String? = null
)
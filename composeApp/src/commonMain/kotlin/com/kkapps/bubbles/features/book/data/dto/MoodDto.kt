package com.kkapps.bubbles.features.book.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoodDto(
    val id: Long = 0,
    val moodName: String, // SAD, OKAY, HAPPY, JOY
    val moodEmoji: String,
    val rank: Float,
    val notes: String? = null,
    val createdAt: Long,
    val imagePhoto: String? = null
)
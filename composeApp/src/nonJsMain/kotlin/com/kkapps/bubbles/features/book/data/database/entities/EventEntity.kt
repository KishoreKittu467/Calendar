package com.kkapps.bubbles.features.book.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
actual data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val notes: String? = null,
    val createdAt: Long,
    val startTime: Long,
    val duration: Long,
    val eventType: String = "Event", // Event, Task, Notes, Tx, Habit, Bookmark
    val dueDate: Long? = null,
    val groupName: String? = null,
    val subGroupName: String? = null,
    val ownerId: String? = null,
    val isMarked: Boolean? = null,
    val lastModifiedAt: Long? = null,
    val tags: String? = null, // Comma-separated
    val categories: String? = null, // Comma-separated
    val locationName: String? = null,
    val locationLat: Double? = null,
    val locationLong: Double? = null,
    val imageEmoji: String? = null
)
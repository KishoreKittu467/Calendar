package com.kkapps.bubbles.features.entries.domain.entities


sealed class EntryType {
    object Diary : EntryType()
    object Note : EntryType()
    object Event : EntryType()
    object Transaction : EntryType()
    object Mood : EntryType()
}

sealed class Entry {
    abstract val id: Long
    abstract val createdAt: Long
    abstract val lastModifiedAt: Long
    abstract val type: EntryType
}

data class Diary(
    override val id: Long = 0,
    val title: String,
    val content: String,
    override val createdAt: Long,
    override val lastModifiedAt: Long,
    val imageEmoji: String? = null,
    val tags: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val isMarked: Boolean = false,
    override val type: EntryType = EntryType.Diary
) : Entry()

data class Note(
    override val id: Long = 0,
    val title: String,
    val content: String,
    override val createdAt: Long,
    override val lastModifiedAt: Long,
    val tags: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val imageEmoji: String? = null,
    val isMarked: Boolean = false,
    override val type: EntryType = EntryType.Note
) : Entry()

data class Event(
    override val id: Long = 0,
    val name: String,
    val notes: String? = null,
    override val createdAt: Long,
    val startTime: Long,
    val duration: Long,
    val eventType: String = "Event",
    val dueDate: Long? = null,
    override val lastModifiedAt: Long,
    val tags: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val locationName: String? = null,
    val imageEmoji: String? = null,
    override val type: EntryType = EntryType.Event,
) : Entry()

data class Transaction(
    override val id: Long = 0,
    val amount: Float,
    val payerName: String,
    val receiverName: String? = null,
    val txType: String? = null,
    val txMode: String? = null,
    val txStatus: String? = null,
    override val createdAt: Long,
    override val lastModifiedAt: Long,
    val notes: String? = null,
    val tags: List<String> = emptyList(),
    val categories: List<String> = emptyList(),
    val isMarked: Boolean = false,
    override val type: EntryType = EntryType.Transaction
) : Entry()

data class Mood(
    override val id: Long = 0,
    val moodName: String,
    val moodEmoji: String,
    val rank: Float,
    val notes: String? = null,
    override val createdAt: Long,
    override val lastModifiedAt: Long = createdAt,
    val imagePhoto: String? = null,
    override val type: EntryType = EntryType.Mood
) : Entry()
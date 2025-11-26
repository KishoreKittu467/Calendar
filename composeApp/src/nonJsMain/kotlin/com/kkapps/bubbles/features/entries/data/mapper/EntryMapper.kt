package com.kkapps.bubbles.features.entries.data.mapper

import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity
import com.kkapps.bubbles.features.entries.domain.entities.Diary
import com.kkapps.bubbles.features.entries.domain.entities.Event
import com.kkapps.bubbles.features.entries.domain.entities.Mood
import com.kkapps.bubbles.features.entries.domain.entities.Note
import com.kkapps.bubbles.features.entries.domain.entities.Transaction

// Diary mappers
fun DiaryEntity.toDiary(): Diary = Diary(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    imageEmoji = imageEmoji,
    tags = tags?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    categories = categories?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    isMarked = isMarked
)

fun Diary.toDiaryEntity(): DiaryEntity = DiaryEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    imageEmoji = imageEmoji,
    tags = tags.joinToString(","),
    categories = categories.joinToString(","),
    isMarked = isMarked
)

// Note mappers
fun NoteEntity.toNote(): Note = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    tags = tags?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    categories = categories?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    imageEmoji = imageEmoji,
    isMarked = isMarked
)

fun Note.toNoteEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    tags = tags.joinToString(","),
    categories = categories.joinToString(","),
    imageEmoji = imageEmoji,
    isMarked = isMarked
)

// Event mappers
fun EventEntity.toEvent(): Event = Event(
    id = id,
    name = name,
    notes = notes,
    createdAt = createdAt,
    startTime = startTime,
    duration = duration,
    eventType = eventType,
    dueDate = dueDate,
    lastModifiedAt = lastModifiedAt ?: createdAt,
    tags = tags?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    categories = categories?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    locationName = locationName,
    imageEmoji = imageEmoji
)

fun Event.toEventEntity(): EventEntity = EventEntity(
    id = id,
    name = name,
    notes = notes,
    createdAt = createdAt,
    startTime = startTime,
    duration = duration,
    eventType = eventType,
    dueDate = dueDate,
    lastModifiedAt = lastModifiedAt,
    tags = tags.joinToString(","),
    categories = categories.joinToString(","),
    locationName = locationName,
    imageEmoji = imageEmoji
)

// Transaction mappers
fun TransactionEntity.toTransaction(): Transaction = Transaction(
    id = id,
    amount = amount,
    payerName = payerName,
    receiverName = receiverName,
    txType = txType,
    txMode = txMode,
    txStatus = txStatus,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    notes = notes,
    tags = tags?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    categories = categories?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
    isMarked = isMarked
)

fun Transaction.toTransactionEntity(): TransactionEntity = TransactionEntity(
    id = id,
    amount = amount,
    payerName = payerName,
    receiverName = receiverName,
    txType = txType,
    txMode = txMode,
    txStatus = txStatus,
    createdAt = createdAt,
    lastModifiedAt = lastModifiedAt,
    notes = notes,
    tags = tags.joinToString(","),
    categories = categories.joinToString(","),
    isMarked = isMarked
)

// Mood mappers
fun MoodEntity.toMood(): Mood = Mood(
    id = id,
    moodName = moodName,
    moodEmoji = moodEmoji,
    rank = rank,
    notes = notes,
    createdAt = createdAt,
    lastModifiedAt = createdAt,
    imagePhoto = imagePhoto
)

fun Mood.toMoodEntity(): MoodEntity = MoodEntity(
    id = id,
    moodName = moodName,
    moodEmoji = moodEmoji,
    rank = rank,
    notes = notes,
    createdAt = createdAt,
    imagePhoto = imagePhoto
)
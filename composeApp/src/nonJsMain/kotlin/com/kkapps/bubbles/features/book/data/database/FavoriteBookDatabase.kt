package com.kkapps.bubbles.features.book.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kkapps.bubbles.features.book.data.database.entities.DiaryEntity
import com.kkapps.bubbles.features.book.data.database.entities.EventEntity
import com.kkapps.bubbles.features.book.data.database.entities.HabitEntity
import com.kkapps.bubbles.features.book.data.database.entities.MoodEntity
import com.kkapps.bubbles.features.book.data.database.entities.NoteEntity
import com.kkapps.bubbles.features.book.data.database.entities.TaskEntity
import com.kkapps.bubbles.features.book.data.database.entities.TransactionEntity

@Database(
    entities = [
        BookEntity::class,
        DiaryEntity::class,
        EventEntity::class,
        TaskEntity::class,
        TransactionEntity::class,
        HabitEntity::class,
        MoodEntity::class,
        NoteEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class FavoriteBookDatabase: RoomDatabase() {
    abstract val favoriteBookDao: FavoriteBookDao

    companion object {
        const val DB_NAME = "bubbles.db"
    }
}
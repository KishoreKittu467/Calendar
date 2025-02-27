package com.kkapps.bubbles.features.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteBookDao {

    @Upsert
    abstract suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM BookEntity")
    abstract fun getFavoriteBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    abstract suspend fun getFavoriteBook(id: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :id")
    abstract suspend fun deleteFavoriteBook(id: String)
}
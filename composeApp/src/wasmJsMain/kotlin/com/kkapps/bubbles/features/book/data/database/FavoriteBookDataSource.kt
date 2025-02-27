package com.kkapps.bubbles.features.book.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FavoriteBookDataSource {

    private val favoriteBooks = mutableListOf<BookEntity>()
    private val _favoriteBooks = MutableStateFlow(favoriteBooks)

    fun upsert(book: BookEntity) {
        favoriteBooks.add(book)
    }

    fun getFavoriteBooks(): Flow<List<BookEntity>> {
        return _favoriteBooks
    }

    fun getFavoriteBook(id: String): BookEntity? {
        return favoriteBooks.find { it.id == id }
    }

    fun deleteFavoriteBook(id: String) {
        favoriteBooks.removeAll { it.id == id }
    }
}
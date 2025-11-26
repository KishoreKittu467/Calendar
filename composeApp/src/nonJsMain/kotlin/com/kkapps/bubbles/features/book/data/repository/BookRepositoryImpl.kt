package com.kkapps.bubbles.features.book.data.repository

import androidx.sqlite.SQLiteException
import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.core.domain.EmptyResult
import com.kkapps.bubbles.core.domain.map
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDao
import com.kkapps.bubbles.features.book.data.mapper.toBook
import com.kkapps.bubbles.features.book.data.mapper.toBookEntity
import com.kkapps.bubbles.features.book.data.mappers.toBook
import com.kkapps.bubbles.features.book.data.sources.BookDataSource
import com.kkapps.bubbles.features.book.domain.entities.Book
import com.kkapps.bubbles.features.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val bookDataSource: BookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(query: String): DataResult<List<Book>, DataError.Remote> {
        return bookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): DataResult<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(bookId)

        return if(localResult == null) {
            bookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            DataResult.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            DataResult.Success(Unit)
        } catch(_: SQLiteException) {
            DataResult.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }
}
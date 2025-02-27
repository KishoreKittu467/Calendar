package com.kkapps.bubbles.features.book.data.repository

import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.core.domain.EmptyResult
import com.kkapps.bubbles.core.domain.map
import com.kkapps.bubbles.features.book.data.database.FavoriteBookDataSource
import com.kkapps.bubbles.features.book.data.mapper.toBook
import com.kkapps.bubbles.features.book.data.mapper.toBookEntity
import com.kkapps.bubbles.features.book.data.mappers.toBook
import com.kkapps.bubbles.features.book.data.network.BookDataSource
import com.kkapps.bubbles.features.book.domain.entities.Book
import com.kkapps.bubbles.features.book.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookRepositoryImpl(
    private val bookDataSource: BookDataSource,
    private val favoriteBookDataSource: FavoriteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): DataResult<List<Book>, DataError.Remote> {
        return bookDataSource
            .searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): DataResult<String?, DataError> {
        val localResult = favoriteBookDataSource.getFavoriteBook(bookId)

        return if(localResult == null) {
            bookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            DataResult.Success(localResult.description)
        }
//        var description: String? = null
//        bookDataSource.getBookDetails(bookId).onSuccess {
//            description = it.description
//        }.onError {
//            return DataResult.Error(DataError.Local.DISK_FULL)
//        }
//        return DataResult.Success(description)
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDataSource
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDataSource
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        favoriteBookDataSource.upsert(book.toBookEntity())
        return DataResult.Success(Unit)
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDataSource.deleteFavoriteBook(id)
    }
}
package com.kkapps.bubbles.features.book.data.database

import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.features.book.data.dto.BookWorkDto
import com.kkapps.bubbles.features.book.data.dto.SearchResponseDto
import com.kkapps.bubbles.features.book.data.dto.SearchedBookDto
import com.kkapps.bubbles.features.book.data.sources.BookDataSource

class SampleBookDataSource : BookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): DataResult<SearchResponseDto, DataError.Remote> {
        return mockBookList()
    }

    override suspend fun getBookDetails(bookWorkId: String): DataResult<BookWorkDto, DataError.Remote> {
        return DataResult.Success(
            BookWorkDto(
                description = "This is a mock book description."
            )
        )
    }

    private fun mockBookList() = DataResult.Success(
        SearchResponseDto(
            listOf(
                SearchedBookDto(
                    id = "1",
                    title = "Book 1",
                    coverKey = "https://example.com/book1.jpg",
                    authorNames = listOf("Author 1"),
                    languages = listOf("English"),
                    firstPublishYear = null,
                    ratingsAverage = 3.5,
                    ratingsCount = 200,
                    numPagesMedian = 123,
                    numEditions = 5
                ),
                SearchedBookDto(
                    id = "2",
                    title = "Book 2",
                    coverKey = "https://example.com/book2.jpg",
                    authorNames = listOf("Author 2"),
                    languages = listOf("Telugu"),
                    firstPublishYear = null,
                    ratingsAverage = 3.5,
                    ratingsCount = 200,
                    numPagesMedian = 123,
                    numEditions = 5
                )
            )
        )
    )
}
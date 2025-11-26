package com.kkapps.bubbles.features.book.data.sources

import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.features.book.data.dto.BookWorkDto
import com.kkapps.bubbles.features.book.data.dto.SearchResponseDto

interface BookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): DataResult<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): DataResult<BookWorkDto, DataError.Remote>
}
package com.kkapps.bubbles.features.book.data.database

import com.kkapps.bubbles.core.data.safeCall
import com.kkapps.bubbles.core.domain.DataError
import com.kkapps.bubbles.core.domain.DataResult
import com.kkapps.bubbles.features.book.data.dto.BookWorkDto
import com.kkapps.bubbles.features.book.data.dto.SearchResponseDto
import com.kkapps.bubbles.features.book.data.sources.BookDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class KtorBookDataSource(
    private val httpClient: HttpClient
): BookDataSource {

    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): DataResult<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter(
                    "fields",
                    "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count"
                )
            }
        }
    }

    override suspend fun getBookDetails(bookWorkId: String): DataResult<BookWorkDto, DataError.Remote> {
        return safeCall<BookWorkDto> {
            httpClient.get(
                urlString = "$BASE_URL/works/$bookWorkId.json"
            )
        }
    }
}
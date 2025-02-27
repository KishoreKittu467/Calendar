package com.kkapps.bubbles.features.book.presentation.book_list

import com.kkapps.bubbles.core.presentation.utils.UiText
import com.kkapps.bubbles.features.book.domain.entities.Book

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
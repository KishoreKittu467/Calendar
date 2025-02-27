package com.kkapps.bubbles.features.book.presentation.book_detail

import com.kkapps.bubbles.features.book.domain.entities.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)

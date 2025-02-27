package com.kkapps.bubbles.features.book.presentation.book_detail

import com.kkapps.bubbles.features.book.domain.entities.Book


sealed interface BookDetailAction {
    data object OnBackClick: BookDetailAction
    data object OnFavoriteClick: BookDetailAction
    data class OnSelectedBookChange(val book: Book): BookDetailAction
}
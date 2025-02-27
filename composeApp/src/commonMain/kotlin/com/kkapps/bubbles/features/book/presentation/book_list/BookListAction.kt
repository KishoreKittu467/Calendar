package com.kkapps.bubbles.features.book.presentation.book_list

import com.kkapps.bubbles.features.book.domain.entities.Book

sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String): BookListAction
    data class OnBookClick(val book: Book): BookListAction
    data class OnTabSelected(val index: Int): BookListAction
}
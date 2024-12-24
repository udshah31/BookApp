package org.example.bookApp.book.presentation.book_detail

import org.example.bookApp.book.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavourite: Boolean = false,
    val book: Book? = null
)

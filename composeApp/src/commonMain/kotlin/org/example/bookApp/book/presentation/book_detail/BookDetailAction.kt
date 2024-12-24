package org.example.bookApp.book.presentation.book_detail

import org.example.bookApp.book.domain.Book

sealed interface BookDetailAction {
    data object OnBackClick : BookDetailAction
    data object OnFavouriteClick : BookDetailAction
    data class OnSelectedBookChange(val book: Book) : BookDetailAction
}
package org.example.bookApp.book.presentation.book_list

import org.example.bookApp.book.domain.Book

sealed interface BookListAction {
    data class OnSearchQuery(val query: String) : BookListAction
    data class OnItemClick(val book: Book) : BookListAction
    data class OnTabSelected(val index: Int) : BookListAction
}
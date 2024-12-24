package org.example.bookApp.book.presentation.book_list

import org.example.bookApp.book.domain.Book
import org.example.bookApp.core.presentation.UiText

data class BookListState(
    val searchQuery: String = "Java",
    val searchResults: List<Book> = emptyList(),
    val favouriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedIndex: Int = 0,
    val errorMessage: UiText? = null,
)



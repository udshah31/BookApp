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


 val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("uday"),
        description = "Desc $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.87,
        ratingsCount = 5,
        numPages = 100,
        numEditions = 3
    )
}
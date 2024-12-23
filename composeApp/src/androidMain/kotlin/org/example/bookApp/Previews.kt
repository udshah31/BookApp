package org.example.bookApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.example.bookApp.book.domain.Book
import org.example.bookApp.book.presentation.book_list.BooListScreen
import org.example.bookApp.book.presentation.book_list.BookListState
import org.example.bookApp.book.presentation.book_list.books
import org.example.bookApp.book.presentation.book_list.components.BookSearchBar


@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BookSearchBar(
            searchQuery = "",
            onQueryChange = {},
            onImeSearch = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}



@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun BookListScreenPreview() {
    BooListScreen(
        state = BookListState(
            searchResults = books,
        ),
        onAction = {

        }
    )

}
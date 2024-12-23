package org.example.bookApp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.engine.HttpClientEngine
import org.example.bookApp.book.data.network.KtorRemoteBookDataSource
import org.example.bookApp.book.data.repository.DefaultBookRepository
import org.example.bookApp.book.presentation.book_list.BookListScreenRoot
import org.example.bookApp.book.presentation.book_list.BookListViewModel
import org.example.bookApp.core.data.HttpClientFactory
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(engine: HttpClientEngine) {
    BookListScreenRoot(
        viewModel = remember { BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory.create(engine)
                    )
                )
            )
        },
        onItemClick = {

        },
    )

}
package org.example.bookApp.book.data.repository

import org.example.bookApp.book.data.mappers.toBook
import org.example.bookApp.book.data.network.RemoteBookDataSource
import org.example.bookApp.book.domain.Book
import org.example.bookApp.book.domain.BookRepository
import org.example.bookApp.core.domain.DataError
import org.example.bookApp.core.domain.Result
import org.example.bookApp.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {

    override suspend fun searchBooks(
        query: String
    ): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }
}
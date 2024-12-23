package org.example.bookApp.book.domain

import org.example.bookApp.core.domain.DataError
import org.example.bookApp.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}
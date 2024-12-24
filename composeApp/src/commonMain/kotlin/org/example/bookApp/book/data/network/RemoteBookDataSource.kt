package org.example.bookApp.book.data.network

import org.example.bookApp.book.data.dto.BookWorkDto
import org.example.bookApp.book.data.dto.SearchResponseDto
import org.example.bookApp.core.domain.DataError
import org.example.bookApp.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(
        bookWorkId: String
    ): Result<BookWorkDto, DataError.Remote>
}

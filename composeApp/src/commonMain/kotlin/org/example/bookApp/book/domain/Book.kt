package org.example.bookApp.book.domain

import kotlinx.serialization.Serializable


@Serializable
data class Book(
    val id: String,
    val title: String,
    val imageUrl: String,
    val authors: List<String>,
    val description: String?,
    val languages: List<String>,
    val firstPublishYear: String?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val numPages: Int?,
    val numEditions: Int,
)

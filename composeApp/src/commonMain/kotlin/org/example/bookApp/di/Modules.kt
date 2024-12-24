package org.example.bookApp.di


import org.example.bookApp.book.data.network.KtorRemoteBookDataSource
import org.example.bookApp.book.data.network.RemoteBookDataSource
import org.example.bookApp.book.domain.BookRepository
import org.example.bookApp.book.data.repository.DefaultBookRepository
import org.example.bookApp.book.presentation.book_list.BookListViewModel
import org.example.bookApp.book.presentation.book_detail.BookDetailViewModel
import org.example.bookApp.book.presentation.SelectedBookViewModel
import org.example.bookApp.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module


val shareModule = module {

    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}
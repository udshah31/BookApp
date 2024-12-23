package org.example.bookApp.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookapp.composeapp.generated.resources.Res
import bookapp.composeapp.generated.resources.favorites
import bookapp.composeapp.generated.resources.no_favorite_books
import bookapp.composeapp.generated.resources.no_search_results
import bookapp.composeapp.generated.resources.search_results
import org.example.bookApp.book.domain.Book
import org.example.bookApp.book.presentation.book_list.components.BookList
import org.example.bookApp.book.presentation.book_list.components.BookSearchBar
import org.example.bookApp.core.presentation.DarkBlue
import org.example.bookApp.core.presentation.DesertWhite
import org.example.bookApp.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(),
    onItemClick: (Book) -> Unit,
    modifier: Modifier = Modifier
) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    BooListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is BookListAction.OnItemClick -> onItemClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        }

    )

}


@Composable
fun BooListScreen(
    state: BookListState,
    onAction: (BookListAction) -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState { 2 }
    val searchResultListState = rememberLazyListState()
    val favouriteBooksListState = rememberLazyListState()

    LaunchedEffect(state.searchResults) {
        searchResultListState.animateScrollToItem(0)
    }

    LaunchedEffect(state.selectedIndex) {
        pagerState.animateScrollToPage(state.selectedIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
            onAction(BookListAction.OnTabSelected(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BookSearchBar(
            searchQuery = state.searchQuery,
            onQueryChange = {
                onAction(BookListAction.OnSearchQuery(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectedIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth(),
                    contentColor = DesertWhite,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[state.selectedIndex])
                        )
                    }
                ) {
                    Tab(
                        selected = state.selectedIndex == 0,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }

                    Tab(
                        selected = state.selectedIndex == 1,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(1))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = 0.5f)
                    ) {
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    when {
                                        state.errorMessage != null -> {
                                            Text(
                                                text = state.errorMessage.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        state.searchResults.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_search_results),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }

                                        else -> {
                                            BookList(
                                                books = state.searchResults,
                                                onBookClick = {
                                                    onAction(BookListAction.OnItemClick(it))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.favouriteBooks.isEmpty()) {
                                    Text(
                                        text = stringResource(Res.string.no_favorite_books),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                    )
                                } else {
                                    BookList(
                                        books = state.favouriteBooks,
                                        onBookClick = {
                                            onAction(BookListAction.OnItemClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        scrollState = favouriteBooksListState
                                    )
                                }

                            }
                        }
                    }

                }
            }
        }

    }

}
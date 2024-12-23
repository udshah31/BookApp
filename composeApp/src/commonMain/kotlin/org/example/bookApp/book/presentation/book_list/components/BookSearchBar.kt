package org.example.bookApp.book.presentation.book_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import bookapp.composeapp.generated.resources.Res
import bookapp.composeapp.generated.resources.close_hint
import bookapp.composeapp.generated.resources.search_hint
import org.example.bookApp.core.presentation.DarkBlue
import org.example.bookApp.core.presentation.DesertWhite
import org.example.bookApp.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource


@Composable
fun BookSearchBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {

    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow
        )
    ){
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            singleLine = true,
            keyboardActions = KeyboardActions {
                onImeSearch()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            ),
            shape = RoundedCornerShape(100),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = SandYellow, cursorColor = DarkBlue
            ),
            placeholder = {
                Text(
                    text = stringResource(Res.string.search_hint)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f)
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotBlank()
                ) {
                    IconButton(onClick = {
                        onQueryChange("")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(Res.string.close_hint),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            },
            modifier = modifier.background(
                shape = RoundedCornerShape(100),
                color = DesertWhite
            ).minimumInteractiveComponentSize()

        )
    }



}
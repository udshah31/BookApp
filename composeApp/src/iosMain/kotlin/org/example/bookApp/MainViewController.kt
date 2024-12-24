package org.example.bookApp

import androidx.compose.ui.window.ComposeUIViewController
import org.example.bookApp.app.App
import org.example.bookApp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(
    )
}
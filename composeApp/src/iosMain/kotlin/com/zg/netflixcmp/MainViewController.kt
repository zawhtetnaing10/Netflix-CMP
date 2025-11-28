package com.zg.netflixcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.zg.netflixcmp.core.persistence.getDatabaseBuilderIos
import com.zg.netflixcmp.movies.data.repositories.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    App()
}
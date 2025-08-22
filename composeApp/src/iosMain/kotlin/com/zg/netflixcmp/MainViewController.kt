package com.zg.netflixcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.zg.netflixcmp.core.persistence.getDatabaseBuilderIos

fun MainViewController() = ComposeUIViewController {

    val databaseBuilder = getDatabaseBuilderIos()
    App(databaseBuilder)
}
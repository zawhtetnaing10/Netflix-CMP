package com.zg.netflixcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.zg.netflixcmp.core.persistence.IOSDatabaseDriverFactory

fun MainViewController() =
    ComposeUIViewController { App(databaseDriverFactory = IOSDatabaseDriverFactory()) }
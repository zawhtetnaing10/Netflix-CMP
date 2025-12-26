package com.zg.netflixcmp

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class UITest {
    @Test
    fun fromLogin_navigateToHome() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag("LOGIN_SCREEN_BODY").assertExists()
        onNodeWithTag("SIGN_IN_BUTTON").performClick()

    }

    @Test
    fun fromLogin_navigateToHome_checkData() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag("LOGIN_SCREEN_BODY").assertExists()
        onNodeWithTag("SIGN_IN_BUTTON").performClick()

        waitUntil(timeoutMillis = 10000) { onNodeWithTag("FIRST_GENRE_NAME-Action").isDisplayed() }
        onNodeWithTag("FIRST_GENRE_NAME-Action").assertTextContains("Action")

//        waitUntil(timeoutMillis = 10000) {
//            onAllNodesWithTag("FIRST_GENRE_NAME-Animation").fetchSemanticsNodes().isNotEmpty()
//        }
        onNodeWithTag("FIRST_GENRE_NAME-Comedy")
            .performScrollTo()
            .isDisplayed()
    }
}
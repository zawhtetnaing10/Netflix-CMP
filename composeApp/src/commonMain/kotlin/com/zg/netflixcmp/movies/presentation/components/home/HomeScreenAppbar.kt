package com.zg.netflixcmp.movies.presentation.components.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.MARGIN_LARGE_2X
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.TEXT_LARGE
import com.zg.netflixcmp.utils.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppbar(modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Black
        ),
        title = {
            Text(
                "For Zaw",
                fontWeight = FontWeight.Bold,
                fontSize = TEXT_LARGE,
                color = White
            )
        },
        actions = {
            Icon(
                Icons.Default.Share,
                contentDescription = "Share button",
                tint = White,
                modifier = Modifier.size(MARGIN_LARGE_2X)
            )
            Spacer(modifier = Modifier.width(MARGIN_MEDIUM))
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "Download button",
                tint = White,
                modifier = Modifier.size(MARGIN_LARGE_2X)
            )
            Spacer(modifier = Modifier.width(MARGIN_MEDIUM))
            Icon(
                Icons.Default.Search,
                contentDescription = "Search button",
                tint = White,
                modifier = Modifier.size(MARGIN_LARGE_2X)
            )
        },
        modifier = modifier
    )
}
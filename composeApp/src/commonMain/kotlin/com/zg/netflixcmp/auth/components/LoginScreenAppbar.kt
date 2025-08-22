package com.zg.netflixcmp.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_3
import com.zg.netflixcmp.utils.MARGIN_XLARGE
import com.zg.netflixcmp.utils.NETFLIX_LOGO_SIZE
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White
import netflixcmp.composeapp.generated.resources.Res
import netflixcmp.composeapp.generated.resources.help
import netflixcmp.composeapp.generated.resources.netflix_logo_app_bar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenAppbar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Black),
        title = {
            Image(
                painterResource(Res.drawable.netflix_logo_app_bar),
                contentDescription = "Netflix AppBar Title",
                modifier = Modifier.size(NETFLIX_LOGO_SIZE)
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(start = MARGIN_MEDIUM_3)
                    .size(MARGIN_XLARGE)
            )
        },
        actions = {
            Text(
                stringResource(Res.string.help),
                style = TextStyle(
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = TEXT_REGULAR_2X,
                ),
                modifier = Modifier.padding(end = MARGIN_MEDIUM_2)
            )
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun LoginScreenAppbarPreview() {
    LoginScreenAppbar()
}
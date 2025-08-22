package com.zg.netflixcmp.movies.presentation.components.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MOVIE_ITEM_HEIGHT
import com.zg.netflixcmp.utils.MOVIE_ITEM_WIDTH

@Composable
fun MovieListItem(movie : MovieVO?, modifier : Modifier = Modifier){
    AsyncImage(
        model = movie?.getFullBackdropPath() ?: "",
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(MOVIE_ITEM_WIDTH)
            .height(MOVIE_ITEM_HEIGHT)
            .clip(
                RoundedCornerShape(MARGIN_MEDIUM)
            )
    )
}
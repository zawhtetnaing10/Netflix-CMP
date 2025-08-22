package com.zg.netflixcmp.movies.presentation.components.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.presentation.components.NetflixMoviePrimaryButton
import com.zg.netflixcmp.movies.presentation.components.NetflixMovieSecondaryButton
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_SMALL
import com.zg.netflixcmp.utils.White

@Composable
fun FeaturedMovie(movie: MovieVO, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(MARGIN_MEDIUM_2),
        modifier = modifier
    ) {
        AsyncImage(
            model = movie.getFullPosterPath(),
            contentDescription = "Featured movie poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
                .background(brush = Brush.verticalGradient(listOf(Color.Transparent, Black)))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM)
            ) {
                // Genres
                Genres(movie = movie, modifier = Modifier)

                // Buttons
                PlayAndMyListButtons(modifier = Modifier)
            }
        }
    }
}

@Composable
fun PlayAndMyListButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(MARGIN_MEDIUM_2),
        horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM)
    ) {
        // Play Button
        NetflixMoviePrimaryButton(
            label = "Play",
            iconVector = Icons.Default.PlayArrow,
            iconPainter = null,
            modifier = Modifier.weight(1.0f)
        )

        // My List Button
        NetflixMovieSecondaryButton(
            label = "My List",
            iconVector = Icons.Default.Add,
            iconPainter = null,
            modifier = Modifier.weight(1.0f)
        )
    }
}

@Composable
fun Genres(movie: MovieVO, modifier: Modifier = Modifier) {
    if (movie.genres != null) {
        val firstThreeGenres = movie.genres.take(3)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
            modifier = modifier
        ) {
            firstThreeGenres.forEachIndexed { index, genre ->
                Text(genre.name, color = White)

                // Separator will not appear for the last genre
                if (index < firstThreeGenres.count() - 1) {
                    Canvas(modifier = Modifier.size(MARGIN_SMALL)) {
                        drawCircle(color = Color.White)
                    }
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun FeaturedMoviePreview() {
//    FeaturedMovie()
//}
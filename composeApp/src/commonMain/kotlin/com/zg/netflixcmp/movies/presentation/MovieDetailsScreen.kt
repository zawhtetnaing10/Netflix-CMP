package com.zg.netflixcmp.movies.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import chaintech.videoplayer.host.MediaPlayerHost
import chaintech.videoplayer.model.PlayerSpeed
import chaintech.videoplayer.model.ScreenResize
import chaintech.videoplayer.ui.video.VideoPlayerComposable
import com.zg.netflixcmp.movies.presentation.components.NetflixMoviePrimaryButton
import com.zg.netflixcmp.movies.presentation.components.NetflixMovieSecondaryButton
import com.zg.netflixcmp.movies.presentation.components.details.MovieAdditionalInfo
import com.zg.netflixcmp.movies.presentation.components.details.MovieDetailsActions
import com.zg.netflixcmp.movies.presentation.components.home.MovieListItem
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.DetailIconsGrey
import com.zg.netflixcmp.utils.MARGIN_CARD_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_LARGE
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_3
import com.zg.netflixcmp.utils.MARGIN_SMALL
import com.zg.netflixcmp.utils.MARGIN_XLARGE
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import com.zg.netflixcmp.utils.MOVIE_ITEM_HEIGHT
import com.zg.netflixcmp.utils.TEXT_REGULAR
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.TEXT_REGULAR_3X
import com.zg.netflixcmp.utils.White
import netflixcmp.composeapp.generated.resources.Res
import netflixcmp.composeapp.generated.resources.download
import netflixcmp.composeapp.generated.resources.netflix_n_logo
import org.jetbrains.compose.resources.painterResource
import kotlin.math.ceil

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    onTapBack: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    // For Video Player
    val videoPlayerHost = MediaPlayerHost(
        mediaUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        isPaused = false,
        isMuted = false,
        initialSpeed = PlayerSpeed.X1,
        initialVideoFitMode = ScreenResize.FIT,
        isLooping = false,
        startTimeInSeconds = 10f,
        isFullScreen = false
    )
    val host = remember { videoPlayerHost }

    Scaffold(containerColor = Black) {
        LazyColumn {
            // Movie Trailer
            item {
                Box(
                    modifier = Modifier.height(250.dp)
                ) {

                    VideoPlayerComposable(
                        playerHost = host,
                        modifier = Modifier.fillMaxWidth().height(250.dp)
                    )

                    Icon(
                        Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier
                            .padding(top = MARGIN_XLARGE, start = MARGIN_MEDIUM)
                            .size(MARGIN_XXLARGE)
                            .align(Alignment.TopStart)
                            .clickable {
                                onTapBack()
                            }
                    )
                }
            }

            // Movie Info
            item {
                Column(
                    horizontalAlignment = Alignment.Start,

                    modifier = Modifier.padding(
                        top = MARGIN_MEDIUM,
                        start = MARGIN_MEDIUM,
                        end = MARGIN_MEDIUM
                    )
                ) {
                    // Movie Type
                    Row(horizontalArrangement = Arrangement.spacedBy(MARGIN_SMALL)) {
                        Image(
                            painterResource(Res.drawable.netflix_n_logo),
                            contentDescription = null,
                            modifier = Modifier.size(MARGIN_MEDIUM_3)
                        )
                        Text("FILM", color = White, fontSize = TEXT_REGULAR_2X)
                    }

                    // Movie Name
                    Text(
                        state.movieDetails?.title ?: "",
                        fontSize = TEXT_REGULAR_3X,
                        color = White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = MARGIN_MEDIUM)
                    )
                }
            }

            // Movie Additional Info
            item {
                MovieAdditionalInfo(
                    modifier = Modifier.padding(
                        top = MARGIN_CARD_MEDIUM_2,
                        start = MARGIN_MEDIUM,
                        end = MARGIN_MEDIUM
                    )
                )
            }

            // Play and Download Buttons
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(MARGIN_CARD_MEDIUM_2),
                    modifier = Modifier.padding(horizontal = MARGIN_MEDIUM)
                ) {
                    NetflixMoviePrimaryButton(
                        "Play",
                        iconVector = Icons.Default.PlayArrow,
                        iconPainter = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    NetflixMovieSecondaryButton(
                        "Download",
                        iconVector = null,
                        iconPainter = painterResource(Res.drawable.download),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                Text(
                    state.movieDetails?.overview ?: "",
                    color = White,
                    fontSize = TEXT_REGULAR,
                    modifier = Modifier.padding(
                        top = MARGIN_MEDIUM_2,
                        start = MARGIN_MEDIUM,
                        end = MARGIN_MEDIUM
                    )
                )
            }

            // Cast
            item {
                Text(
                    "Cast: Taron Egerton, Sofia Carson, Jason Bateman, .... more",
                    color = DetailIconsGrey,
                    fontSize = TEXT_REGULAR,
                    modifier = Modifier.padding(
                        top = MARGIN_MEDIUM_2,
                        start = MARGIN_MEDIUM,
                        end = MARGIN_MEDIUM
                    )
                )
            }

            // Director
            item {
                Text(
                    "Director: Jaume Collet-Serra",
                    color = DetailIconsGrey,
                    fontSize = TEXT_REGULAR,
                    modifier = Modifier.padding(
                        top = MARGIN_SMALL,
                        start = MARGIN_MEDIUM,
                        end = MARGIN_MEDIUM
                    )
                )
            }

            // Buttons
            item {
                MovieDetailsActions(
                    modifier = Modifier.padding(
                        top = MARGIN_MEDIUM_3,
                        start = MARGIN_XLARGE,
                        end = MARGIN_XLARGE
                    )
                )
            }

            // More Like This
            if (state.similarMovies.isNotEmpty()) {
                item {
                    Text(
                        "More Like This",
                        fontSize = TEXT_REGULAR_3X,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        modifier = Modifier.padding(
                            top = MARGIN_LARGE,
                            start = MARGIN_MEDIUM,
                            end = MARGIN_MEDIUM
                        )
                    )
                }
            }

            // Similar Movies
            if (state.similarMovies.isNotEmpty()) {
                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(horizontal = MARGIN_MEDIUM),
                        horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
                        verticalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
                        modifier = Modifier.padding(top = MARGIN_MEDIUM_2)
                            .height((MOVIE_ITEM_HEIGHT + MARGIN_MEDIUM_2) * ((state.similarMovies.count() / 3) + 1))
                    ) {
                        items(state.similarMovies.count()) { index ->
                            MovieListItem(movie = state.similarMovies[index], modifier = Modifier)
                        }
                    }
                }
            }


            // Bottom Spacing
            item {
                Spacer(modifier = Modifier.height(MARGIN_XXLARGE))
            }
        }
    }
}

//@Preview()
//@Composable
//fun MovieDetailsScreenPreview() {
//    MovieDetailsScreen(
//        onTapBack = {}
//    )
//}
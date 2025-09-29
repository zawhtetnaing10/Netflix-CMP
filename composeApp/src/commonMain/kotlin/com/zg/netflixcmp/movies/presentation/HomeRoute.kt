package com.zg.netflixcmp.movies.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zg.netflixcmp.movies.presentation.actions.HomeActions
import com.zg.netflixcmp.movies.presentation.components.home.CategoriesSection
import com.zg.netflixcmp.movies.presentation.components.home.FeaturedMovie
import com.zg.netflixcmp.movies.presentation.components.home.HomeScreenAppbar
import com.zg.netflixcmp.movies.presentation.components.home.TitleAndMovieList
import com.zg.netflixcmp.movies.presentation.events.HomeEvents
import com.zg.netflixcmp.movies.presentation.states.HomeState
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.FEATURED_MOVIE_HEIGHT
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigation: (HomeEvents) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    // Listen to events
    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            onNavigation(event)
        }
    }

    HomeScreen(state = state, onAction = {
        viewModel.handleAction(it)
    })
}

@Composable
fun HomeScreen(state: HomeState, onAction: (HomeActions) -> Unit) {
    Scaffold(
        topBar = {
            HomeScreenAppbar()
        },
        containerColor = Black
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                end = innerPadding.calculateEndPadding(LayoutDirection.Ltr)
            )
        ) {
            item {
                // Categories
                CategoriesSection(modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2))
            }

            item {
                Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))
            }

            if (state.featuredMovie != null) {
                item {
                    // Featured Movie
                    FeaturedMovie(
                        movie = state.featuredMovie,
                        modifier = Modifier
                            .padding(horizontal = MARGIN_MEDIUM_2)
                            .height(FEATURED_MOVIE_HEIGHT)
                            .clickable {
                                onAction(HomeActions.OnTapMovie(state.featuredMovie.id))
                            }
                    )
                }
            }

            // Movies Section
            if (state.moviesByGenre.isNotEmpty()) {
                items(state.moviesByGenre.count()) { index ->
                    TitleAndMovieList(
                        genre = state.moviesByGenre[index].first,
                        movies = state.moviesByGenre[index].second,
                        onTapMovie = { movieId ->
                            onAction(HomeActions.OnTapMovie(movieId))
                        }, modifier = Modifier
                    )
                }
            }


            // Bottom Spacer
            item {
                Spacer(Modifier.height(MARGIN_XXLARGE))
            }
        }
    }
}
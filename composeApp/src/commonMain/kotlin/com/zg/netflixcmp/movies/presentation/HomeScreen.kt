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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zg.netflixcmp.movies.presentation.components.home.CategoriesSection
import com.zg.netflixcmp.movies.presentation.components.home.FeaturedMovie
import com.zg.netflixcmp.movies.presentation.components.home.HomeScreenAppbar
import com.zg.netflixcmp.movies.presentation.components.home.TitleAndMovieList
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.FEATURED_MOVIE_HEIGHT
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_XXLARGE

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onTapMovie: (Int) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

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
                        movie = state.featuredMovie!!,
                        modifier = Modifier
                            .padding(horizontal = MARGIN_MEDIUM_2)
                            .height(FEATURED_MOVIE_HEIGHT)
                            .clickable {
                                onTapMovie(state.featuredMovie!!.id)
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
                            onTapMovie(movieId)
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

//@Preview
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        viewModel {  }
//        onTapMovie = {}
//    )
//}
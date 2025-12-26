package com.zg.netflixcmp.movies.presentation.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.TEXT_REGULAR_3X
import com.zg.netflixcmp.utils.White

@Composable
fun TitleAndMovieList(genre: GenreVO, movies : List<MovieVO>, onTapMovie: (Int) -> Unit, modifier : Modifier = Modifier){
    Column(modifier = modifier.padding(top = MARGIN_MEDIUM_2)) {
        Text(
            genre.name,
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = TEXT_REGULAR_3X,
            modifier = Modifier.padding(horizontal = MARGIN_MEDIUM_2).testTag("FIRST_GENRE_NAME-${genre.name}")
        )
        Spacer(Modifier.height(MARGIN_MEDIUM))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
            contentPadding = PaddingValues(horizontal = MARGIN_MEDIUM_2)
        ) {
            items(movies.count()) { index ->
                MovieListItem(
                    movie = movies[index],
                    modifier = Modifier
                    .clickable{
                        onTapMovie(movies[index].id)
                    })
            }
        }
    }
}
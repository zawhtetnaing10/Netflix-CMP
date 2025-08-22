package com.zg.netflixcmp.movies.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zg.netflixcmp.utils.MARGIN_CARD_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_XLARGE
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White

@Composable
fun CategoriesSection(modifier: Modifier = Modifier) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(MARGIN_CARD_MEDIUM_2), modifier = modifier) {
        CategoryItem("TV Shows", isDropdownShown = false, modifier = Modifier)
        CategoryItem("Movies", isDropdownShown = false, modifier = Modifier)
        CategoryItem("Categories", isDropdownShown = true, modifier = Modifier)
    }
}

@Composable
fun CategoryItem(content: String, isDropdownShown: Boolean = false, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color.Transparent, shape = RoundedCornerShape(MARGIN_MEDIUM_2))
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(MARGIN_MEDIUM_2)
            )
            .height(MARGIN_XLARGE)
            .padding(horizontal = MARGIN_CARD_MEDIUM_2)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(content, color = White, fontSize = TEXT_REGULAR_2X)
            if (isDropdownShown) {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "Category drop down",
                    tint = Color.White
                )
            }
        }
    }
}
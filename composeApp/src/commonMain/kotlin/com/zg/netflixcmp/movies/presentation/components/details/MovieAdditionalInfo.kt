package com.zg.netflixcmp.movies.presentation.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zg.netflixcmp.utils.DetailIconsGrey
import com.zg.netflixcmp.utils.MARGIN_CARD_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_LARGE
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_SMALL
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import com.zg.netflixcmp.utils.NetflixGrey
import com.zg.netflixcmp.utils.TEXT_REGULAR
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.TEXT_SMALL
import com.zg.netflixcmp.utils.White
import netflixcmp.composeapp.generated.resources.Res
import netflixcmp.composeapp.generated.resources.ad
import netflixcmp.composeapp.generated.resources.dolby_vision
import netflixcmp.composeapp.generated.resources.message
import netflixcmp.composeapp.generated.resources.spatial_audio
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieAdditionalInfo(modifier : Modifier){
    FlowRow (
        horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
        itemVerticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {
        Text("2024", color = White, fontSize = TEXT_REGULAR_2X)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.background(NetflixGrey)
                .padding(MARGIN_SMALL)
        ) {
            Text("18+", color = White, fontSize = TEXT_SMALL)
        }

        Text("1H 59M", color = White, fontSize = TEXT_REGULAR_2X)

        Icon(
            painterResource(Res.drawable.dolby_vision),
            contentDescription = null,
            tint = DetailIconsGrey,
            modifier = Modifier.size(MARGIN_XXLARGE)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.border(1.dp, color = DetailIconsGrey, shape = RoundedCornerShape(MARGIN_SMALL))
                .background(Color.Transparent)
                .padding(MARGIN_SMALL)
        ) {
            Text("HD", color = DetailIconsGrey, fontSize = TEXT_REGULAR)
        }

        Icon(
            painterResource(Res.drawable.spatial_audio),
            contentDescription = null,
            tint = DetailIconsGrey,
            modifier = Modifier.size(MARGIN_LARGE)
        )

        Icon(
            painterResource(Res.drawable.ad),
            contentDescription = null,
            tint = DetailIconsGrey,
            modifier = Modifier.size(MARGIN_LARGE)
        )

        Icon(
            painterResource(Res.drawable.message),
            contentDescription = null,
            tint = DetailIconsGrey,
            modifier = Modifier.size(MARGIN_LARGE)
        )
    }
}
package com.zg.netflixcmp.movies.presentation.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.zg.netflixcmp.utils.MARGIN_56
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_3
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White
import netflixcmp.composeapp.generated.resources.Res
import netflixcmp.composeapp.generated.resources.add
import netflixcmp.composeapp.generated.resources.rate
import netflixcmp.composeapp.generated.resources.share
import org.jetbrains.compose.resources.painterResource

@Composable
fun MovieDetailsActions(modifier: Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(MARGIN_56), modifier = modifier) {
        MovieDetailsActionButton(
            label = "Play",
            iconPainter = painterResource(Res.drawable.add),
            iconVector = null,
            modifier = Modifier
        )
        MovieDetailsActionButton(
            label = "Rate",
            iconPainter = painterResource(Res.drawable.rate),
            iconVector = null,
            modifier = Modifier
        )
        MovieDetailsActionButton(
            label = "Share",
            iconPainter = painterResource(Res.drawable.share),
            iconVector = null,
            modifier = Modifier
        )
    }
}

@Composable
fun MovieDetailsActionButton(
    label: String,
    iconVector: ImageVector?,
    iconPainter: Painter?,
    modifier: Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        if (iconPainter != null && iconVector == null) {
            Icon(
                iconPainter,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(MARGIN_MEDIUM_3)
            )
        } else if (iconVector != null && iconPainter == null) {
            Icon(
                iconVector,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(MARGIN_MEDIUM_3)
            )
        }

        Spacer(modifier = Modifier.height(MARGIN_MEDIUM_2))

        Text(label, color = White, fontSize = TEXT_REGULAR_2X)
    }
}
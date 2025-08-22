package com.zg.netflixcmp.movies.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_XLARGE
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White

@Composable
fun NetflixMoviePrimaryButton(
    label: String,
    iconVector: ImageVector?,
    iconPainter: Painter?,
    modifier: Modifier
) {
    Surface(
        color = White,
        shape = RoundedCornerShape(MARGIN_MEDIUM),
        modifier = modifier.height(MARGIN_XXLARGE)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MARGIN_MEDIUM),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (iconVector == null && iconPainter != null)
                    Icon(
                        iconPainter,
                        contentDescription = null,
                        modifier = Modifier.size(MARGIN_XLARGE)
                    )
                else if (iconVector != null && iconPainter == null)
                    Icon(
                        iconVector,
                        contentDescription = null,
                        modifier = Modifier.size(MARGIN_XLARGE)
                    )
                Text(label, fontSize = TEXT_REGULAR_2X, fontWeight = FontWeight.Bold)
            }
        }
    }
}
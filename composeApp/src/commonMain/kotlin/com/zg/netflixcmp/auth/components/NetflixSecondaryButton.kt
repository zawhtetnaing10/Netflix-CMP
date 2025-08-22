package com.zg.netflixloginscreenjetpackcompose.ui.resusable_composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import com.zg.netflixcmp.utils.NetflixGrey
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NetflixSecondaryButton(label: String, onTapButton: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onTapButton,
        shape = RoundedCornerShape(MARGIN_MEDIUM),
        colors = ButtonDefaults.buttonColors(
            containerColor = NetflixGrey
        ), modifier = modifier
            .fillMaxWidth()
            .height(MARGIN_XXLARGE)
    ) {
        Text(
            label, style = TextStyle(
                color = White, fontSize = TEXT_REGULAR_2X,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

@Preview
@Composable
private fun NetflixSecondaryButtonPreview() {
    NetflixSecondaryButton(label = "Use a sign-in code", onTapButton = {})
}
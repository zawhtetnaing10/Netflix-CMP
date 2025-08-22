package com.zg.netflixloginscreenjetpackcompose.ui.resusable_composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.zg.netflixcmp.utils.HintGrey
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import com.zg.netflixcmp.utils.NetflixRedPrimary
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NetflixPrimaryButton(title: String, onButtonTapped: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onButtonTapped,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = NetflixRedPrimary
        ),
        shape = RoundedCornerShape(MARGIN_MEDIUM),
        modifier = modifier
            .fillMaxWidth()
            .height(MARGIN_XXLARGE)
    ) {
        Text(
            title, style = TextStyle(
                color = White, fontSize = TEXT_REGULAR_2X,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

@Preview
@Composable
private fun NetflixPrimaryButtonPreview() {
    NetflixPrimaryButton(
        title = "Sign in",
        onButtonTapped = {}
    )
}
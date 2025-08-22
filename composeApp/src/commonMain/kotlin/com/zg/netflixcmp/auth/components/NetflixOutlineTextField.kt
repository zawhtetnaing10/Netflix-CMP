package com.zg.netflixcmp.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.zg.netflixcmp.utils.HintGrey
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.NetflixGrey
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NetflixOutlineTextField(
    placeholder: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        value = textValue,
        onValueChange = {
            textValue = it
            onTextChanged(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = NetflixGrey,
            unfocusedContainerColor = NetflixGrey,
            focusedTextColor = White,
            unfocusedTextColor = White,
            cursorColor = White,
            focusedLabelColor = Color.White,
        ),
        placeholder = {
            Text(
                placeholder, style = TextStyle(
                    fontSize = TEXT_REGULAR_2X, color = HintGrey
                )
            )
        },
        shape = RoundedCornerShape(MARGIN_MEDIUM),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun NetflixOutlineTextFieldPreview() {
    NetflixOutlineTextField(placeholder = "Email or password", onTextChanged = {})
}
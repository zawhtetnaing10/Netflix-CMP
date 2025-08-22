package com.zg.netflixcmp.auth.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.zg.netflixcmp.utils.HintGrey
import com.zg.netflixcmp.utils.MARGIN_MEDIUM
import com.zg.netflixcmp.utils.NetflixGrey
import com.zg.netflixcmp.utils.TEXT_REGULAR_2X
import com.zg.netflixcmp.utils.White
import netflixcmp.composeapp.generated.resources.Res
import netflixcmp.composeapp.generated.resources.password_hide
import netflixcmp.composeapp.generated.resources.password_show
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NetflixPasswordTextField(
    placeholder: String,
    onChangePassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    var isPasswordHidden by remember { mutableStateOf(true) }

    // Password
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            onChangePassword(it)
        },
        visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
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
                placeholder,
                style = TextStyle(
                    fontSize = TEXT_REGULAR_2X, color = HintGrey
                ),
            )
        },
        suffix = {
            Text(
                if (isPasswordHidden)
                    stringResource(Res.string.password_show)
                else
                    stringResource(Res.string.password_hide),
                style = TextStyle(
                    fontSize = TEXT_REGULAR_2X,
                    color = HintGrey,
                ),
                modifier = Modifier.clickable {
                    isPasswordHidden = !isPasswordHidden
                }
            )
        },
        shape = RoundedCornerShape(MARGIN_MEDIUM),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview
@Composable
private fun NetflixPasswordTextFieldPreview() {
    NetflixPasswordTextField(placeholder = "Password", onChangePassword = {})
}
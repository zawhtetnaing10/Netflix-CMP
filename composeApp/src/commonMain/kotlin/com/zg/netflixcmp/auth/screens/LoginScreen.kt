package com.zg.netflixcmp.auth.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.zg.netflixcmp.auth.components.LoginScreenAppbar
import com.zg.netflixcmp.auth.components.NetflixOutlineTextField
import com.zg.netflixcmp.auth.components.NetflixPasswordTextField
import com.zg.netflixcmp.utils.Black
import com.zg.netflixcmp.utils.HintGrey
import com.zg.netflixcmp.utils.LINE_HEIGHT_REGULAR
import com.zg.netflixcmp.utils.MARGIN_MEDIUM_2
import com.zg.netflixcmp.utils.MARGIN_XLARGE
import com.zg.netflixcmp.utils.MARGIN_XXLARGE
import com.zg.netflixcmp.utils.TEXT_REGULAR
import com.zg.netflixloginscreenjetpackcompose.ui.resusable_composables.NetflixPrimaryButton
import com.zg.netflixloginscreenjetpackcompose.ui.resusable_composables.NetflixSecondaryButton
import netflixcmp.composeapp.generated.resources.Res
import netflixcmp.composeapp.generated.resources.email_or_phone_number_placeholder
import netflixcmp.composeapp.generated.resources.forgot_password
import netflixcmp.composeapp.generated.resources.learn_more
import netflixcmp.composeapp.generated.resources.or
import netflixcmp.composeapp.generated.resources.password_placeholder
import netflixcmp.composeapp.generated.resources.sign_in
import netflixcmp.composeapp.generated.resources.sign_in_protected_by_recaptcha
import netflixcmp.composeapp.generated.resources.use_a_sign_in_code
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun LoginScreen(
    onTapLogin: () -> Unit
) {
    Scaffold(containerColor = Black, topBar = {
        LoginScreenAppbar()
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        LoginContent(onTapLogin = {
            onTapLogin()
        }, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun LoginContent(onTapLogin: () -> Unit, modifier: Modifier = Modifier) {
    Surface(color = Black, modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(MARGIN_XXLARGE)) {
                NetflixOutlineTextField(
                    placeholder = stringResource(Res.string.email_or_phone_number_placeholder),
                    onTextChanged = {}
                )
                Spacer(Modifier.height(MARGIN_MEDIUM_2))
                NetflixPasswordTextField(
                    placeholder = stringResource(Res.string.password_placeholder),
                    onChangePassword = {}
                )
                Spacer(Modifier.height(MARGIN_MEDIUM_2))
                NetflixPrimaryButton(
                    title = stringResource(Res.string.sign_in),
                    onButtonTapped = {
                        onTapLogin()
                    }
                )
                Spacer(Modifier.height(MARGIN_MEDIUM_2))
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(Res.string.or)
                    )
                }
                Spacer(Modifier.height(MARGIN_MEDIUM_2))
                NetflixSecondaryButton(
                    label = stringResource(Res.string.use_a_sign_in_code),
                    onTapButton = {}
                )
                Spacer(Modifier.height(MARGIN_XLARGE))
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(Res.string.forgot_password),
                        style = TextStyle(fontWeight = FontWeight.SemiBold)
                    )
                }
                Spacer(Modifier.height(MARGIN_XLARGE))
                SignInTermsAndConditions()
            }
        }
    }
}

@Composable
fun SignInTermsAndConditions(modifier: Modifier = Modifier) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = HintGrey, fontSize = TEXT_REGULAR)) {
            append(stringResource(Res.string.sign_in_protected_by_recaptcha))
        }
        withStyle(
            style = SpanStyle(
                color = HintGrey,
                fontWeight = FontWeight.Bold,
                fontSize = TEXT_REGULAR
            )
        ) {
            append(stringResource(Res.string.learn_more))
        }
    }

    Text(
        annotatedText,
        textAlign = TextAlign.Center,
        style = TextStyle(lineHeight = LINE_HEIGHT_REGULAR),
        modifier = modifier
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(onTapLogin = {})
}
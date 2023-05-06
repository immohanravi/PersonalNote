package com.bogarsoft.noteify.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.bogarsoft.noteify.R


@Composable
fun AppName() {
    val font = FontFamily(
       Font(R.font.berkshireswash_regular, weight = androidx.compose.ui.text.font.FontWeight.Bold)
    )

    val name = stringResource(id = R.string.app_name)
    Text(text = name,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = font,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
}
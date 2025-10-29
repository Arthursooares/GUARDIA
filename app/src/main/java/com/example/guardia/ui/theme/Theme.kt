package com.example.guardia.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = neutral100, //
    secondary =
    tertiary =
)

private val LightColorScheme = lightColorScheme(
    primary = brand900, //cor do texto e botões
    onPrimary = neutral100, //cards e textbox
    secondary = brand950, // card home e chat popup
    onSecondary = neutral100, // texto card home
    tertiary = yellow50, // texto login/cadastro inferior
    background = brand1200, //cor do fundo
    onBackground = brand900, //cor do texto de funo
    surface = neutral100,
    onSurface = brand900,
    
)

@Composable
fun GuardiaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
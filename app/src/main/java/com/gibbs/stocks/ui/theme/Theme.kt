package com.gibbs.stocks.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MintyGreen,
    secondary = MoneyGreen,
    tertiary = BoggyGreen,
    background = BoggyGreen,
    surface = BoggyGreen,
    onBackground = Color.White,
    onSurface = Color.White,
    onTertiary = Color.White,
    surfaceContainerHigh = MoneyGreen,
    surfaceContainerLow = MarshyGreen,
    surfaceContainer = MintyGreen,
    onSurfaceVariant = BoggyGreen,
)

private val LightColorScheme = lightColorScheme(
    primary = MoneyGreen,
    secondary = MintyGreen,
    tertiary = BoggyGreen,
    background = MintyGreen,
    surface = MintyGreen,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSecondary = BoggyGreen,
    surfaceContainerHigh = MoneyGreen,
    surfaceContainerLow = MarshyGreen,
    surfaceContainer = MintyGreen,
    onSurfaceVariant = BoggyGreen,
)

@Composable
fun StocksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.secondary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
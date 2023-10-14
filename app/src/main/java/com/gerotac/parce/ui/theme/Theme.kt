package com.gerotac.parce.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ColorLogin,
    primaryVariant = logoutColor,
    secondary = Orange,
    background = White,
    surface = White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Black,
    onSurface = Black,

    /*primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200*/
)

private val LightColorPalette = lightColors(
    primary = ColorLogin,
    primaryVariant = DarkBlue,
    secondary = Orange,
    background = White,
    surface = LightGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Black,
    onSurface = Black,

    /*primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200*/
)

@Composable
fun ParceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
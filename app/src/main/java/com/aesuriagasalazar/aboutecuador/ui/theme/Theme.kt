package com.aesuriagasalazar.aboutecuador.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryLightColor,
    onPrimary = OnPrimaryLightColor,
    secondary = SecondaryLightColor,
    onSecondary = BackgroundLightColor,
    background = BackgroundLightColor,
    surface = SurfaceLightColor,
    onSurface = OnSurfaceLightColor
)

private val LightColorPalette = lightColors(
    primary = PrimaryLightColor,
    onPrimary = OnPrimaryLightColor,
    secondary = SecondaryLightColor,
    onSecondary = BackgroundLightColor,
    background = BackgroundLightColor,
    surface = SurfaceLightColor,
    onSurface = OnSurfaceLightColor

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,

    onSurface = Color.Black,
    */
)

@Composable
fun AboutEcuadorTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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
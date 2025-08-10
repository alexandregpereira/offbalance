package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

// Clean theme API without exposing Material dependencies
object OffbalanceTheme {
    val colors: OffbalanceColorTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.colors

    val typography: OffbalanceTypographyTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.typography

    val gradients: OffbalanceGradientsTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.gradients

    val spacing: OffbalanceSpacingTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.spacing

    val shapes: OffbalanceShapeTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.shapes
}

// Internal Material colors for the underlying implementation
private val DarkColors = darkColors(
    primary = OffbalanceColors.Primary,
    primaryVariant = OffbalanceColors.PrimaryStart,
    secondary = OffbalanceColors.Success,
    secondaryVariant = OffbalanceColors.SuccessStart,
    background = OffbalanceColors.SurfaceDark,
    surface = OffbalanceColors.SurfaceMedium,
    error = OffbalanceColors.Error,
    onPrimary = OffbalanceColors.TextWhite,
    onSecondary = OffbalanceColors.TextWhite,
    onBackground = OffbalanceColors.TextPrimary,
    onSurface = OffbalanceColors.TextPrimary,
    onError = OffbalanceColors.TextWhite
)

private val LightColors = lightColors(
    primary = OffbalanceColors.Primary,
    primaryVariant = OffbalanceColors.PrimaryStart,
    secondary = OffbalanceColors.Success,
    secondaryVariant = OffbalanceColors.SuccessStart,
    background = Color.White,
    surface = Color(0xFFF5F5F5),
    error = OffbalanceColors.Error,
    onPrimary = OffbalanceColors.TextWhite,
    onSecondary = OffbalanceColors.TextWhite,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = OffbalanceColors.TextWhite
)

@Composable
fun OffbalanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val materialColors = if (darkTheme) DarkColors else LightColors
    val tokens = OffbalanceThemeTokens()
    
    CompositionLocalProvider(LocalOffbalanceThemeTokens provides tokens) {
        MaterialTheme(
            colors = materialColors,
            typography = OffbalanceTypography,
            shapes = OffbalanceShapes,
            content = content
        )
    }
}

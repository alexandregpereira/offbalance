package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

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

    val spacing: OffbalanceSpacingTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.spacing

    val shapes: OffbalanceShapeTokens
        @Composable
        @ReadOnlyComposable
        get() = LocalOffbalanceThemeTokens.current.shapes
}

@Composable
fun OffbalanceTheme(
    content: @Composable () -> Unit
) {
    val tokens = OffbalanceThemeTokens()
    
    CompositionLocalProvider(LocalOffbalanceThemeTokens provides tokens) {
        content()
    }
}

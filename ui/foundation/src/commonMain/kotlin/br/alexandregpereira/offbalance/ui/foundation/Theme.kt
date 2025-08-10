package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

// Extended theme data class for additional design tokens
data class OffbalanceThemeTokens(
    val gradients: OffbalanceGradientsTokens = OffbalanceGradientsTokens(),
    val spacing: OffbalanceSpacingTokens = OffbalanceSpacingTokens(),
    val customShapes: OffbalanceCustomShapesTokens = OffbalanceCustomShapesTokens()
)

data class OffbalanceGradientsTokens(
    val primary: Brush = OffbalanceGradients.Primary,
    val success: Brush = OffbalanceGradients.Success,
    val error: Brush = OffbalanceGradients.Error,
    val background: Brush = OffbalanceGradients.Background,
    val glass: Brush = OffbalanceGradients.Glass,
    val overlay: Brush = OffbalanceGradients.Overlay,
    val cardHover: Brush = OffbalanceGradients.CardHover
)

data class OffbalanceSpacingTokens(
    val xs: Dp = OffbalanceSpacing.xs,
    val sm: Dp = OffbalanceSpacing.sm,
    val md: Dp = OffbalanceSpacing.md,
    val lg: Dp = OffbalanceSpacing.lg,
    val xl: Dp = OffbalanceSpacing.xl,
    val xxl: Dp = OffbalanceSpacing.xxl,
    val xxxl: Dp = OffbalanceSpacing.xxxl,
    val component: Dp = OffbalanceSpacing.component,
    val section: Dp = OffbalanceSpacing.section,
    val screen: Dp = OffbalanceSpacing.screen
)

data class OffbalanceCustomShapesTokens(
    val button: RoundedCornerShape = OffbalanceCustomShapes.button,
    val card: RoundedCornerShape = OffbalanceCustomShapes.card,
    val section: RoundedCornerShape = OffbalanceCustomShapes.section,
    val phoneFrame: RoundedCornerShape = OffbalanceCustomShapes.phoneFrame,
    val circular: RoundedCornerShape = OffbalanceCustomShapes.circular,
    val inputField: RoundedCornerShape = OffbalanceCustomShapes.inputField,
    val dialog: RoundedCornerShape = OffbalanceCustomShapes.dialog,
    val bottomSheet: RoundedCornerShape = OffbalanceCustomShapes.bottomSheet
)

// Composition locals for accessing extended theme tokens
val LocalOffbalanceTheme = staticCompositionLocalOf { OffbalanceThemeTokens() }

// Dark color scheme based on design system
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

// Light color scheme (for potential future support)
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
    val colors = if (darkTheme) DarkColors else LightColors
    val tokens = OffbalanceThemeTokens()
    
    CompositionLocalProvider(LocalOffbalanceTheme provides tokens) {
        MaterialTheme(
            colors = colors,
            typography = OffbalanceTypography,
            shapes = OffbalanceShapes,
            content = content
        )
    }
}

// Extension property to access extended theme tokens
val MaterialTheme.offbalance: OffbalanceThemeTokens
    @Composable
    get() = LocalOffbalanceTheme.current

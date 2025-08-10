package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

// Complete theme tokens that don't expose Material
data class OffbalanceThemeTokens(
    val colors: OffbalanceColorTokens = OffbalanceColorTokens(),
    val typography: OffbalanceTypographyTokens = OffbalanceTypographyTokens(),
    val gradients: OffbalanceGradientsTokens = OffbalanceGradientsTokens(),
    val spacing: OffbalanceSpacingTokens = OffbalanceSpacingTokens(),
    val shapes: OffbalanceShapeTokens = OffbalanceShapeTokens()
)

data class OffbalanceColorTokens(
    val primary: Color = OffbalanceColors.Primary,
    val primaryStart: Color = OffbalanceColors.PrimaryStart,
    val primaryEnd: Color = OffbalanceColors.PrimaryEnd,
    val backgroundStart: Color = OffbalanceColors.BackgroundStart,
    val backgroundMid: Color = OffbalanceColors.BackgroundMid,
    val backgroundEnd: Color = OffbalanceColors.BackgroundEnd,
    val surfaceDark: Color = OffbalanceColors.SurfaceDark,
    val surfaceMedium: Color = OffbalanceColors.SurfaceMedium,
    val surfaceLight: Color = OffbalanceColors.SurfaceLight,
    val textPrimary: Color = OffbalanceColors.TextPrimary,
    val textSecondary: Color = OffbalanceColors.TextSecondary,
    val textMuted: Color = OffbalanceColors.TextMuted,
    val textWhite: Color = OffbalanceColors.TextWhite,
    val success: Color = OffbalanceColors.Success,
    val error: Color = OffbalanceColors.Error,
    val warning: Color = OffbalanceColors.Warning,
    val border: Color = OffbalanceColors.Border,
    val borderFocus: Color = OffbalanceColors.BorderFocus
)

data class OffbalanceTypographyTokens(
    // Standard hierarchy
    val h1: TextStyle = OffbalanceTypography.h1,
    val h2: TextStyle = OffbalanceTypography.h2,
    val h3: TextStyle = OffbalanceTypography.h3,
    val h4: TextStyle = OffbalanceTypography.h4,
    val h5: TextStyle = OffbalanceTypography.h5,
    val h6: TextStyle = OffbalanceTypography.h6,
    val body1: TextStyle = OffbalanceTypography.body1,
    val body2: TextStyle = OffbalanceTypography.body2,
    val button: TextStyle = OffbalanceTypography.button,
    val caption: TextStyle = OffbalanceTypography.caption,
    val overline: TextStyle = OffbalanceTypography.overline,
    
    // Specialized styles
    val moneyLarge: TextStyle = OffbalanceTextStyles.MoneyLarge,
    val moneyMedium: TextStyle = OffbalanceTextStyles.MoneyMedium,
    val moneySmall: TextStyle = OffbalanceTextStyles.MoneySmall,
    val success: TextStyle = OffbalanceTextStyles.Success,
    val error: TextStyle = OffbalanceTextStyles.Error,
    val warning: TextStyle = OffbalanceTextStyles.Warning
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

data class OffbalanceShapeTokens(
    val button: RoundedCornerShape = OffbalanceCustomShapes.button,
    val card: RoundedCornerShape = OffbalanceCustomShapes.card,
    val section: RoundedCornerShape = OffbalanceCustomShapes.section,
    val phoneFrame: RoundedCornerShape = OffbalanceCustomShapes.phoneFrame,
    val circular: RoundedCornerShape = OffbalanceCustomShapes.circular,
    val inputField: RoundedCornerShape = OffbalanceCustomShapes.inputField,
    val dialog: RoundedCornerShape = OffbalanceCustomShapes.dialog,
    val bottomSheet: RoundedCornerShape = OffbalanceCustomShapes.bottomSheet
)

// Composition local for theme access
val LocalOffbalanceThemeTokens = staticCompositionLocalOf { OffbalanceThemeTokens() }
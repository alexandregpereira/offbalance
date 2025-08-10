package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Typography system based on the design system
val OffbalanceTypography = Typography(
    // H1 - Header Large (35.2px, 700 weight)
    h1 = TextStyle(
        fontFamily = FontFamily.Default, // System font (Segoe UI equivalent)
        fontWeight = FontWeight.Bold,
        fontSize = 35.2.sp,
        lineHeight = 42.sp, // 1.2 line height
        color = OffbalanceColors.TextWhite
    ),
    
    // H2 - Header Medium (25.6px, 600 weight)  
    h2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 25.6.sp,
        lineHeight = 33.sp, // 1.3 line height
        color = OffbalanceColors.TextPrimary
    ),
    
    // H3 - Display Large (28px, 700 weight) - For main balance
    h3 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 31.sp, // 1.1 line height
        color = OffbalanceColors.TextWhite
    ),
    
    // H4 - Display Medium (18px, 600 weight) - For projection amounts
    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 22.sp, // 1.2 line height
        color = OffbalanceColors.Primary
    ),
    
    // H5 - Body Large (14px, 600 weight) - Section titles
    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp, // 1.4 line height
        color = OffbalanceColors.TextPrimary
    ),
    
    // H6 - Body Medium (12px, 600 weight) - Item titles
    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 17.sp, // 1.4 line height
        color = OffbalanceColors.TextPrimary
    ),
    
    // Body1 - Body Small (11px, 400 weight) - Labels & metadata
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 15.sp, // 1.4 line height
        color = OffbalanceColors.TextSecondary
    ),
    
    // Body2 - Standard body text (14px, 400 weight)
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp, // 1.4 line height
        color = OffbalanceColors.TextPrimary
    ),
    
    // Button - Button text (14px, 600 weight)
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = OffbalanceColors.TextWhite
    ),
    
    // Caption - Timestamps & micro text (10px, 400 weight)
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp, // 1.4 line height
        color = OffbalanceColors.TextMuted
    ),
    
    // Overline - Labels and metadata (11px, 600 weight)
    overline = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 15.sp,
        color = OffbalanceColors.TextSecondary
    )
)

// Additional typography styles for specific use cases
object OffbalanceTextStyles {
    
    // For monetary values - using monospace characteristics
    val MoneyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 31.sp,
        color = OffbalanceColors.TextWhite
    )
    
    val MoneyMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = OffbalanceColors.Primary
    )
    
    val MoneySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 17.sp,
        color = OffbalanceColors.TextPrimary
    )
    
    // Success/Error text styles
    val Success = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = OffbalanceColors.Success
    )
    
    val Error = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = OffbalanceColors.Error
    )
    
    val Warning = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = OffbalanceColors.Warning
    )
}
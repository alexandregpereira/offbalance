package br.alexandregpereira.offbalance.ui.foundation

import androidx.compose.ui.graphics.Color

object OffbalanceColors {
    
    // Primary Colors
    val Primary = Color(0xFF6366F1) // Indigo
    val PrimaryStart = Color(0xFF4F46E5) // Primary gradient start
    val PrimaryEnd = Color(0xFF7C3AED) // Primary gradient end
    
    // Background Colors
    val BackgroundStart = Color(0xFF1A1A2E) // Main background gradient start
    val BackgroundMid = Color(0xFF16213E) // Main background gradient middle
    val BackgroundEnd = Color(0xFF0F3460) // Main background gradient end
    val SurfaceDark = Color(0xFF111827) // Phone screens, content areas
    val SurfaceMedium = Color(0xFF1F2937) // Cards, input fields
    val SurfaceLight = Color(0xFF374151) // Borders, dividers
    
    // Text Colors
    val TextPrimary = Color(0xFFE5E7EB) // Main text content
    val TextSecondary = Color(0xFF9CA3AF) // Supporting text, metadata
    val TextMuted = Color(0xFF6B7280) // Disabled text, placeholders
    val TextWhite = Color(0xFFFFFFFF) // Primary text on gradients
    
    // Status Colors
    val Success = Color(0xFF10B981) // Positive values, income
    val SuccessStart = Color(0xFF059669) // Success gradient start
    val SuccessEnd = Color(0xFF10B981) // Success gradient end
    val Error = Color(0xFFEF4444) // Negative amounts, errors
    val ErrorStart = Color(0xFFDC2626) // Error gradient start
    val ErrorEnd = Color(0xFFEF4444) // Error gradient end
    val Warning = Color(0xFFF59E0B) // Warnings, pending states
    
    // Border Colors
    val Border = Color(0xFF374151) // Default borders
    val BorderFocus = Color(0xFF6366F1) // Focus state borders
    val BorderTransparent = Color(0x1AFFFFFF) // 10% white transparency
    
    // Glass effect colors
    val GlassSurface = Color(0x0DFFFFFF) // 5% white transparency for glassmorphism
    val GlowEffect = Color(0x336366F1) // 20% primary color for glow effects
}